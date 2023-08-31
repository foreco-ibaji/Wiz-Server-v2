package com.sesacthon.foreco.trash.dto.response;

import com.sesacthon.foreco.disposal.dto.response.DisposalInfoDto;
import com.sesacthon.foreco.disposal.entity.Disposal;
import com.sesacthon.foreco.trash.entity.DisposalType;
import com.sesacthon.foreco.trash.entity.TrashInfo;
import java.util.List;
import lombok.Getter;

/**
 * 상세품목 쓰레기 응답 Dto
 */
@Getter
public class TrashDetailDto {

  /**
   * 쓰레기 고유 Id
   */
  private final Long id;

  private final DisposalType isRecyclable;

  /**
   * 쓰레기 배출방법
   */
  private final String disposalMethod;

  /**
   * 쓰레기 배출 가능 시간 정보
   */
  private final DisposalInfoDto disposalInfo;



  public TrashDetailDto(TrashInfo trashInfo, List<Disposal> disposals) {
    this.id = trashInfo.getId();
    this.isRecyclable = trashInfo.getType();
    this.disposalMethod = trashInfo.getMethod();
    this.disposalInfo = new DisposalInfoDto(disposals);
  }

}
