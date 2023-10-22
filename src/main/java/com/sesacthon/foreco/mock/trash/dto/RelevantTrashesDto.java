package com.sesacthon.foreco.mock.trash.dto;

import java.util.List;
import lombok.Getter;

@Getter
public class RelevantTrashesDto {

  private final List<RelevantTrashDto> recommendTrashes;


  public RelevantTrashesDto(List<RelevantTrashDto> recommendTrashes) {
    this.recommendTrashes = recommendTrashes;
  }
}
