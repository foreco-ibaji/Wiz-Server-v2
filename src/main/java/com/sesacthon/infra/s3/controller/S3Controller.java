package com.sesacthon.infra.s3.controller;

import com.sesacthon.infra.s3.dto.UploadDto;
import com.sesacthon.infra.s3.dto.AiImageAnalyzeDto;
import com.sesacthon.infra.s3.service.S3Uploader;
import com.sesacthon.global.response.DataResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Tag(name = "카메라", description = "카메라 + AI 관련 api")
@RestController
@RequiredArgsConstructor
public class S3Controller {

  private final S3Uploader s3Uploader;

  @Operation(summary = "카메라로 쓰레기 분석", description = "촬영 사진을 요청하면 AI 모델의 분석 결과값을 확인할 수 있습니다.")
  @PostMapping(value = "/api/v1/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<DataResponse<AiImageAnalyzeDto>> uploadEventImg(
      HttpServletRequest request,
      HttpMethod httpMethod,
      Locale locale /*언어 정보로, 가장 우선순위가 높은 것을 받는다.*/,
      @RequestHeader MultiValueMap<String, String> headerMap /*헤더의 속성값을 키와 벨류로 받는다.*/,
      @RequestPart("img") MultipartFile multipartFile
  ) throws IOException {

    log.info("request={}", request);
    log.info("httpMethod={}", httpMethod);
    log.info("locale={}", locale);
    log.info("headerMap={}", headerMap);

    AiImageAnalyzeDto aiImageAnalyzeDto = s3Uploader.sendToAiServer(multipartFile);
    return new ResponseEntity<>(
        DataResponse.of(HttpStatus.CREATED, "촬영 이미지 업로드 성공", aiImageAnalyzeDto), HttpStatus.CREATED);
  }

  @PostMapping(value = "/api/v1/image/prefix", consumes = {MediaType.ALL_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
  public ResponseEntity<DataResponse<String>> uploadImageWithPrefix(@RequestPart String prefix, @RequestPart(name = "image") MultipartFile multipartFile){
    String url = s3Uploader.uploadFile(multipartFile, prefix);
    return new ResponseEntity<>(DataResponse.of(HttpStatus.OK, "이미지 업로드 성공", url),HttpStatus.CREATED);
  }

}
