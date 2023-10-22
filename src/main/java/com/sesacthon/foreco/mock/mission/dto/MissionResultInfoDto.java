package com.sesacthon.foreco.mock.mission.dto;

import lombok.Getter;

@Getter
public class MissionResultInfoDto {

  /**
   * 지급할 포인트
   */
  Long rewardPoint;
  /**
   * 미션 수행 포인트가 반영된 이후의 유저가 보유한 포인트
   */
  Long userPoint;

  public MissionResultInfoDto(Long rewardPoint, Long userPoint) {
    this.rewardPoint = rewardPoint;
    this.userPoint = userPoint;
  }
}
