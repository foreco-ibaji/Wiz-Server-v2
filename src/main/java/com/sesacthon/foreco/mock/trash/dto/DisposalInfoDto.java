package com.sesacthon.foreco.mock.trash.dto;

import java.util.Arrays;
import java.util.List;
import lombok.Getter;

/**
 * 배출 가능 요일, 시간 응답 Dto.
 */
@Getter
public class DisposalInfoDto {

  private final List<String> days;
  private final String time;

  //mock api작업을 위해 생성한 생성자
  public DisposalInfoDto() {
    this.days = Arrays.asList("월", "수", "금");
    this.time = "18:00 ~ 21:00";
  }

}
