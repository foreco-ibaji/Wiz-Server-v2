package com.sesacthon.foreco.mock.mission.dto;

import lombok.Getter;

@Getter
public class QuizMissionChoice {

  Integer id;
  String name;

  public QuizMissionChoice(Integer id, String name) {
    this.id = id;
    this.name = name;
  }
}
