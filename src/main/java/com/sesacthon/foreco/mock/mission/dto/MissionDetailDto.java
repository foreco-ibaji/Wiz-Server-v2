package com.sesacthon.foreco.mock.mission.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MissionDetailDto {
  long id;
  String kind;
  String title;
  String description;
  String difficulty;
  long missionPoint; //지급할 포인트
  long totalOpportunity; //미션에 참여가능한 최대 횟수
  long totalNumberOfParticipating; //현재 사용된 총 횟수
  long numberOfParticipating;//사용자가 현재 참여 횟수
  long limitPerPerson; //개인 별 해당 미션에 참여 가능 횟수
  String iconUrl;
}
