package com.sesacthon.infra.s3.dto;

import java.util.List;
import java.util.Set;
import lombok.Getter;

@Getter
public class AiImageAnalyzeDto {

  private final String message;
  private final Set<AnalyzedImageDetailDto> result;

  public AiImageAnalyzeDto(String message, Set<AnalyzedImageDetailDto> result) {
    this.message = message;
    this.result = result;
  }
}
