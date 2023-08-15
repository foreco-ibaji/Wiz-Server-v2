package com.sesacthon.foreco.member.exception;

import com.sesacthon.global.exception.BusinessException;
import com.sesacthon.global.exception.ErrorCode;

public class MemberNotFoundException extends BusinessException {
  public MemberNotFoundException(ErrorCode errorCode) {
    super(errorCode);
  }
}
