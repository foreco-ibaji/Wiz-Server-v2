package com.sesacthon.foreco.mock.dto.trash;


import lombok.Getter;

@Getter
public class RelevantTrashDto {

  private final Long id;
  private final String name;
  private final String iconUrl;

  public RelevantTrashDto(Long id, String name, String iconUrl) {
    this.id = id;
    this.name = name;
    this.iconUrl = iconUrl;
  }
}
