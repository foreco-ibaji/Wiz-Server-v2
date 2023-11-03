package com.sesacthon.infra.s3.service;

import static com.sesacthon.global.exception.ErrorCode.HANDLE_ACCESS_DENIED;
import static com.sesacthon.global.exception.ErrorCode.IMAGE_WRONG_FILE_FORMAT;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.sesacthon.foreco.category.entity.Trash;
import com.sesacthon.foreco.category.repository.TrashRepository;
import com.sesacthon.infra.feign.client.mission.QuizMissionClient;
import com.sesacthon.infra.feign.dto.request.ImageAnalyzeRequestDto;
import com.sesacthon.infra.feign.dto.response.ImageAnalyzeResponseDto;
import com.sesacthon.infra.s3.dto.AiImageAnalyzeDto;
import com.sesacthon.infra.s3.dto.AnalyzedImageDetailDto;
import com.sesacthon.infra.s3.exception.ImageUploadException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class S3Uploader {

  private final AmazonS3Client amazonS3Client;

  private final TrashRepository trashRepository;
  private final QuizMissionClient quizMissionAiServer;

  @Value("${cloud.aws.s3.bucket}")
  private String bucket;

  @Value("${AI_MISSION_URL}")
  private String aiUrl;

  /**
   * @param multipartFile 사용자가 촬영한 사진
   * @return S3에 올린 이후 url을 반환
   */
  private String uploadFile(MultipartFile multipartFile) {
    //multipartFile 을 String 형식 fileName 으로 변환한다.
    String fileName = createFileName(multipartFile.getOriginalFilename());
    uploadToS3(multipartFile, fileName, getObjectMetadata(multipartFile));
    return amazonS3Client.getUrl(bucket, fileName).toString();
  }

  /**
   * @param multipartFile 업로드할 이미지
   * @param prefix        파일이 위치할 경로(폴더)
   * @return 이미지 url
   */
  public String uploadFile(MultipartFile multipartFile, String prefix) {
    //multipartFile 을 String 형식 fileName 으로 변환한다.
    String fileName = prefix + "/" + createFileName(multipartFile.getOriginalFilename());
    uploadToS3(multipartFile, fileName, getObjectMetadata(multipartFile));
    return amazonS3Client.getUrl(bucket, fileName).toString();
  }

  /**
   * @param base64Data base64로 인코딩된 문자열
   * @param prefix     s3에 저장할 경로
   * @return s3에 저장된 이미지 url
   */
  public String uploadFileUsingStream(String base64Data, String prefix) {

    byte[] bI = org.apache.commons.codec.binary.Base64.decodeBase64(
        (base64Data.substring(base64Data.indexOf(",") + 1)).getBytes());

    InputStream inputStream = new ByteArrayInputStream(bI);
    String fileName = prefix + "/" + UUID.randomUUID().toString();

    ObjectMetadata objectMetadata = new ObjectMetadata();
    objectMetadata.setContentLength(bI.length);
    objectMetadata.setContentType("image/png");
    try {
      amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
          .withCannedAcl(CannedAccessControlList.PublicRead));
    } catch (Exception e) {
      throw new ImageUploadException(HANDLE_ACCESS_DENIED);
    }
    return amazonS3Client.getUrl(bucket, fileName).toString();
  }


  /**
   * @param fileName multipartFile의 파일 이름
   * @return 'direcotry 이름 + 랜덤 UUID + 확장자' 를 반환
   */
  private String createFileName(String fileName) {
    return UUID.randomUUID().toString().concat(getFileExtension(fileName));
  }


  /**
   * @param fileName createFileName 메서드를 통해서 변경된 파일 이름
   */
  private String getFileExtension(String fileName) {
    List<String> possibleExtensions = Arrays.asList(".jpg", ".png", ".jpeg");
    String extension = fileName.substring(fileName.lastIndexOf("."));
    if (!possibleExtensions.contains(extension)) {
      throw new ImageUploadException(IMAGE_WRONG_FILE_FORMAT);
    }
    return extension;
  }


  /**
   * @param file           MultipartFile
   * @param fileName       createFileName 메서드를 통해서 변경된 파일 이름
   * @param objectMetadata MultipartFile의 length와 contentType을 가진 객체
   */
  private void uploadToS3(MultipartFile file, String fileName, ObjectMetadata objectMetadata) {
    try (InputStream inputStream = file.getInputStream()) {
      amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
          .withCannedAcl(CannedAccessControlList.PublicRead));
    } catch (IOException e) {
      throw new ImageUploadException(HANDLE_ACCESS_DENIED);
    }
  }


  /**
   * @param file MultipartFile
   * @return 메타데이터를 가진 객체를 반환
   */
  private static ObjectMetadata getObjectMetadata(MultipartFile file) {
    ObjectMetadata objectMetadata = new ObjectMetadata();
    objectMetadata.setContentLength(file.getSize());
    objectMetadata.setContentType(file.getContentType());
    return objectMetadata;
  }

  /**
   * @param multipartFile 사용자가 촬영한 이미지 파일
   * @return 서버 전송 결과 메시지 + 분석 결과값 반환
   */
  public AiImageAnalyzeDto sendToAiServer(MultipartFile multipartFile) throws IOException {

    String fileUrl = uploadFile(multipartFile);
    ImageAnalyzeResponseDto response = quizMissionAiServer.analyzeImage(
        new ImageAnalyzeRequestDto(fileUrl));

    List<AnalyzedImageDetailDto> result = new ArrayList<>();
    for (List<String> imageInfo : response.getBboxes()) {
      //반환할 result를 만듬
      String name = imageInfo.get(0);

      List<Integer> coordinate = new ArrayList<>();
      for (int i = 1; i <= 4; i++) {
        coordinate.add(Integer.parseInt(imageInfo.get(i)));
      }

      Long id = -1L;
      List<Trash> trashes = trashRepository.findByAiKeyword(name);
      if (trashes.size() >= 1) {
        id = trashes.get(0).getId();
      }
      result.add(new AnalyzedImageDetailDto(name, coordinate, id));
    }
    return new AiImageAnalyzeDto("ai이미지 분석 요청 성공", result);
  }
}



