package com.sesacthon.foreco.mock.mission.dto;

import com.sesacthon.foreco.mission.entity.Difficulty;
import com.sesacthon.foreco.mission.entity.Kind;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MissionDetailDto {
  Long id;
  Kind kind;
  String title;
  String description;
  Difficulty difficulty;
  Long rewardPoint; //지급할 포인트
  Long totalCount; //미션에 참여가능한 최대 횟수
  Long totalNumberOfParticipating; //현재 사용된 총 횟수
  Long personalParticipatingCount;//사용자가 현재 참여 횟수
  Long personalCount; //개인 별 해당 미션에 참여 가능 횟수
  String iconUrl;
}
