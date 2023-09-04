package com.sesacthon.foreco.trash.exception;

import com.sesacthon.global.exception.BusinessException;
import com.sesacthon.global.exception.ErrorCode;

public class DisposalNotFoundException extends BusinessException {

  public DisposalNotFoundException(ErrorCode errorCode) {
    super(errorCode);
  }

}
