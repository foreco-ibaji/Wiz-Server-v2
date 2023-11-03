package com.sesacthon.infra.s3.dto;

import java.util.List;
import lombok.Getter;

@Getter
public class AiImageAnalyzeDto {

  private final String message;
  private final List<AnalyzedImageDetailDto> result;

  public AiImageAnalyzeDto(String message, List<AnalyzedImageDetailDto> result) {
    this.message = message;
    this.result = result;
  }
}
