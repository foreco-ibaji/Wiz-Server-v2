package com.sesacthon.infra.feign.client.mission;

import com.sesacthon.infra.feign.dto.request.ImageAnalyzeRequestDto;
import com.sesacthon.infra.feign.dto.request.ImageDivisionRequestDto;
import com.sesacthon.infra.feign.dto.response.ImageAnalyzeResponseDto;
import com.sesacthon.infra.feign.dto.response.ImageDivisionResponseDto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "QuizMissionAiServerApi", url = "${AI_MISSION_URL}")
@Component
public interface QuizMissionClient {

  @PostMapping(value ="/Wiz-mission-randomCrop")
  public ImageDivisionResponseDto divideImage(ImageDivisionRequestDto imageDivisionDto);

  @PostMapping(value ="/Wiz-upload")
  public ImageAnalyzeResponseDto analyzeImage(ImageAnalyzeRequestDto imageAnalyzeRequestDto);
}
