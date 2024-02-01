package com.sesacthon.foreco.member.service;

import com.sesacthon.foreco.member.dto.response.MemberInfoResponseDto;
import com.sesacthon.foreco.member.entity.Member;
import com.sesacthon.foreco.mission.service.ParticipationService;
import com.sesacthon.infra.redis.RedisService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberInfoService {

  private final MemberService memberService;
  private final ParticipationService participationService;
  private final RedisService redisService;

  public MemberInfoResponseDto getMember(UUID memberId) {
    Member member = memberService.getMemberById(memberId);
    return new MemberInfoResponseDto(member);
  }

  public void deleteMemberInfo(Long snsId) {
    UUID memberId = memberService.getMemberBySNSId(snsId);

    // 1. 사용자와 연관된 모든 데이터를 삭제한다.
    participationService.deleteMemberLog(memberId);
    memberService.deleteMember(memberId);

    // 2. 사용자의 refreshToken 값을 삭제한다.
    redisService.deleteRefreshToken(memberId);
  }
}
