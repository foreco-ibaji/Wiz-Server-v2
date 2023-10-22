package com.sesacthon.foreco.mock.mission.dto;

import lombok.Getter;

@Getter
public class MissionResultInfoDto {
  Long rewardPoint; //지급할 포인트
  Long userPoint; //미션이 지급된 이후의 포인트

  public MissionResultInfoDto(Long rewardPoint, Long userPoint) {
    this.rewardPoint = rewardPoint;
    this.userPoint = userPoint;
  }
}
