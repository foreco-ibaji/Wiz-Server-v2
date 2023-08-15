package com.sesacthon.foreco.member.service;

import com.sesacthon.foreco.member.dto.response.MemberInfoResponseDto;
import com.sesacthon.foreco.member.entity.Member;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberInfoService {

  private final MemberService memberService;

  public MemberInfoResponseDto getMember(UUID memberId) {
    Member member = memberService.getMemberById(memberId);
    return new MemberInfoResponseDto(member);
  }
}
