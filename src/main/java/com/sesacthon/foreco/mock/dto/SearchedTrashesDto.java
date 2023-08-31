package com.sesacthon.foreco.mock.dto;

import java.util.List;
import lombok.Getter;

@Getter
public class SearchedTrashesDto {
  private final List<SearchedTrashDto> trashes;

  public SearchedTrashesDto(List<SearchedTrashDto> trashes) {
    this.trashes = trashes;
  }

}
