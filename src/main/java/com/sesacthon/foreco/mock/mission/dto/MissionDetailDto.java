package com.sesacthon.foreco.mock.mission.dto;

import com.sesacthon.foreco.mission.entity.Difficulty;
import com.sesacthon.foreco.mission.entity.Kind;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MissionDetailDto {


  Long id;
  /**
   * 미션 종류
   */
  Kind kind;
  /**
   * 미션 제목
   */
  String title;
  /**
   * 미션 설명
   */
  String description;
  /**
   * 난이도
   */
  Difficulty difficulty;
  /**
   * 지급할 포인트
   */
  Long rewardPoint; //지급할 포인트
  /**
   * 미션에 참여가능한 총 횟수
   */
  Long totalCount; //미션에 참여가능한 최대 횟수
  /**
   * 현재 사용된 총 횟수
   */
  Long totalNumberOfParticipating;
  /**
   * 사용자가 현재 참여한 횟수
   */
  Long personalParticipatingCount;
  /**
   * 개인별 해당 미션에 참여 가능한 횟수
   */
  Long personalCount;
  /**
   * url
   */
  String iconUrl;
}
