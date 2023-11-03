package com.sesacthon.infra.feign.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ImageAnalyzeRequestDto {
  @Schema(description = "분석할 이미지 url")
  private final String image_url;
}
