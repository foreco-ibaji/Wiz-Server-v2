package com.sesacthon.foreco.mock.mission.dto;

import java.util.List;
import lombok.Getter;

@Getter
public class QuizMissionDto {

  List<String> images;
  List<QuizMissionChoice> choices;
  QuizMissionChoice answer;

  public QuizMissionDto(List<String> images, List<QuizMissionChoice> choices,
      QuizMissionChoice answer) {
    this.images = images;
    this.choices = choices;
    this.answer = answer;
  }
}
