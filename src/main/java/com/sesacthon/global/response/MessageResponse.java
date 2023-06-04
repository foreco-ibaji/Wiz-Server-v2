package com.sesacthon.global.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 상태코드와 메시지만 반환하는 경우
 */
@Getter
public class MessageResponse extends DefaultResponse {

  private MessageResponse(HttpStatus status, String message) {
    super(status.value(), message);
  }

  public static MessageResponse of(HttpStatus status, String message) {
    return new MessageResponse(status, message);
  }
}