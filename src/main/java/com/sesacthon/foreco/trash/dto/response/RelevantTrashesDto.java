package com.sesacthon.foreco.trash.dto.response;

import com.sesacthon.foreco.trash.entity.TrashInfo;
import java.util.ArrayList;
import lombok.Getter;

import java.util.List;

@Getter
public class RelevantTrashesDto {

  private final List<RelevantTrashDetailDto> relevantTrashesInfo;

  public RelevantTrashesDto(List<TrashInfo> trashInfos) {

    this.relevantTrashesInfo = trashesConvertToRelevantTrashes(trashInfos);
  }

  private static List<RelevantTrashDetailDto> trashesConvertToRelevantTrashes(List<TrashInfo> trashInfos) {
    List<RelevantTrashDetailDto> relevantTrashes = new ArrayList<>();
    for (TrashInfo trashInfo : trashInfos) {
      relevantTrashes.add(
          new RelevantTrashDetailDto(trashInfo.getId(), trashInfo.getTrash().getTrashType(), trashInfo.getTrash().getTrashIcon().toString()));
    }
    return relevantTrashes;
  }
}
