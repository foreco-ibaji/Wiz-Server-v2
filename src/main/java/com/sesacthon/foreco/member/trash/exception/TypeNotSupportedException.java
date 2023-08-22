package com.sesacthon.foreco.member.trash.exception;

import com.sesacthon.global.exception.BusinessException;
import com.sesacthon.global.exception.ErrorCode;

public class TypeNotSupportedException extends BusinessException {

  public TypeNotSupportedException(ErrorCode errorCode) {
    super(errorCode);
  }

}
