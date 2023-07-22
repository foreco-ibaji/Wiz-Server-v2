package com.sesacthon.foreco.trash.dto.response;

import com.sesacthon.foreco.trash.entity.Trash;
import java.util.ArrayList;
import lombok.Getter;

import java.util.List;

@Getter
public class RelevantTrashesDto {

  private final List<RelevantTrashDetailDto> relevantTrashesInfo;

  public RelevantTrashesDto(List<Trash> trashes) {

    this.relevantTrashesInfo = trashesConvertToRelevantTrashes(trashes);
  }

  private static List<RelevantTrashDetailDto> trashesConvertToRelevantTrashes(List<Trash> trashes) {
    List<RelevantTrashDetailDto> relevantTrashes = new ArrayList<>();
    for (Trash trash : trashes) {
      relevantTrashes.add(
          new RelevantTrashDetailDto(trash.getId(), trash.getTrashName(), trash.getTrashIcon()));
    }
    return relevantTrashes;
  }
}
