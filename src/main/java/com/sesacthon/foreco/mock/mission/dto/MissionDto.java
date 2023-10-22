package com.sesacthon.foreco.mock.mission.dto;

import java.util.List;
import lombok.Getter;

@Getter
public class MissionDto {

  List<MissionDetailDto> missions;

  public MissionDto(List<MissionDetailDto> missions) {
    this.missions = missions;
  }
}
