package com.sesacthon.foreco.trash.dto.response;


import lombok.Getter;

@Getter
public class RelevantTrashDetailDto {

  private final Long id;
  private final String name;
  private final String imgUrl;

  public RelevantTrashDetailDto(Long id, String name, String imgUrl) {
    this.id = id;
    this.name = name;
    this.imgUrl = imgUrl;
  }
}
