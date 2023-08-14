package com.sesacthon.foreco.member.dto.response;

import com.sesacthon.foreco.member.entity.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class MemberSimpleInfoResponse {

  @Schema(description = "member의 고유 Id")
  private final String memberId;

  public MemberSimpleInfoResponse(Member member) {
    this.memberId = member.getId().toString();
  }

}
