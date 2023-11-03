package com.sesacthon.infra.s3.dto;

import java.util.List;
import lombok.Getter;

@Getter
public class AnalyzedImageDetailDto {
  private final String name;
  private final List<Integer> coordinate;

  public AnalyzedImageDetailDto(String name, List<Integer> coordinate) {
    this.name = name;
    this.coordinate = coordinate;
  }
}
