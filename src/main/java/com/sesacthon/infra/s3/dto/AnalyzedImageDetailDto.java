package com.sesacthon.infra.s3.dto;

import java.util.List;
import java.util.Objects;
import lombok.Getter;

@Getter
public class AnalyzedImageDetailDto {

  private final String name;
  private final List<Integer> coordinate;
  private final Long id;

  public AnalyzedImageDetailDto(String name, List<Integer> coordinate, Long id) {
    this.name = name;
    this.coordinate = coordinate;
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AnalyzedImageDetailDto that = (AnalyzedImageDetailDto) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }


}
