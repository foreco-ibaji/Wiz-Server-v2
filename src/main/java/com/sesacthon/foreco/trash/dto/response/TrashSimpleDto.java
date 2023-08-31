package com.sesacthon.foreco.trash.dto.response;

import com.sesacthon.foreco.trash.entity.TrashInfo;
import lombok.Getter;

/**
 * 쓰레기 간단 정보 응답 Dto
 */
@Getter
public class TrashSimpleDto {

  private final Long id;
  private final String name;

  public TrashSimpleDto(TrashInfo trashInfo) {
    this.id = trashInfo.getId();
    this.name = trashInfo.getTrash().getTrashType();
  }

  public TrashSimpleDto(Long id, String name) {
    this.id = id;
    this.name = name;
  }
}
