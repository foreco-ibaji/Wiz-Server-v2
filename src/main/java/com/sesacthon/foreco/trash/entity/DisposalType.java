package com.sesacthon.foreco.trash.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum DisposalType {

  RECYCLABLE("재활용 가능 쓰레기", 1),
  NON_RECYCLABLE("재활용 불가능 쓰레기", 2),
  BULKY("대형 폐기물", 3),
  SPECIAL("특수 폐기물", 4);

  private final String type;
  private final Integer code;

}
