package com.sesacthon.foreco.mission.service;

import com.sesacthon.foreco.mission.repository.ParticipationRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParticipationService {

    private final ParticipationRepository participationRepository;

    public void deleteMemberLog(UUID memberId) {
        participationRepository.deleteByMemberId(memberId);
    }
}
