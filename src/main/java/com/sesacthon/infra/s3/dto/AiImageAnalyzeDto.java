package com.sesacthon.infra.s3.dto;

import java.util.List;
import lombok.Getter;

@Getter
public class AiImageAnalyzeDto {
  private final String message;
  private final List<AnalyzedImageDetailDto> result;
  private final Long id;


  public AiImageAnalyzeDto(String message, List<AnalyzedImageDetailDto> result, Long id) {
    this.message = message;
    this.result = result;
    this.id = id;
  }
}
