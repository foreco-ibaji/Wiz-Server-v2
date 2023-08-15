package com.sesacthon.foreco.member.dto.response;


import com.sesacthon.foreco.member.entity.Member;
import com.sesacthon.foreco.member.entity.OAuth2Provider;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class MemberInfoResponseDto {
  @Schema(description = "member의 프로필 이미지 url")
  private final String profileUrl;

  @Schema(description = "member의 고유 Id")
  private final String memberId;

  @Schema(description = "member의 이름")
  private final String memberName;

  @Schema(description = "member의 oauth 제공자(GUEST, KAKAO)")
  private final OAuth2Provider oauth2Provider;

  public MemberInfoResponseDto(Member member) {
    this.profileUrl = member.getProfileUrl();
    this.memberId = member.getId().toString();
    this.memberName =
        member.getOauth2Provider().equals(OAuth2Provider.GUEST) ?
        member.getUserNumber():member.getUsername();
    this.oauth2Provider = member.getOauth2Provider();
  }

}
