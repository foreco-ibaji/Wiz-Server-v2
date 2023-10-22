package com.sesacthon.foreco.mock.mission.dto;

import lombok.Getter;

@Getter
public class DashboardDto {

  String name;
  Long Point;
  String profileUrl;

  public DashboardDto(String name, Long point, String profileUrl) {
    this.name = name;
    Point = point;
    this.profileUrl = profileUrl;
  }

}
