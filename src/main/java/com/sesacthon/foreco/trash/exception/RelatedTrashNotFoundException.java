package com.sesacthon.foreco.trash.exception;

import com.sesacthon.global.exception.BusinessException;
import com.sesacthon.global.exception.ErrorCode;

public class RelatedTrashNotFoundException extends BusinessException {

  public RelatedTrashNotFoundException(ErrorCode errorCode) {
    super(errorCode);
  }
}
