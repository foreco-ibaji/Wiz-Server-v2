package com.sesacthon.infra.s3.dto;

import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AnalyzedImageDto {
  private final String trashName;
  private final List<Integer> coordinate;
}
