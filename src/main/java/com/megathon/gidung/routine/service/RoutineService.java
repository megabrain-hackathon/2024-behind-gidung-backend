package com.megathon.gidung.routine.service;

import com.megathon.gidung.challenge.entity.Challenge;
import com.megathon.gidung.challenge.repository.ChallengeRepository;
import com.megathon.gidung.member.entity.Member;
import com.megathon.gidung.member.repository.MemberRepository;
import com.megathon.gidung.routine.dto.RoutineRequest;
import com.megathon.gidung.routine.dto.RoutineResponse;
import com.megathon.gidung.routine.entity.Routine;
import com.megathon.gidung.routine.repository.RoutineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoutineService {

    private final RoutineRepository routineRepository;
    private final MemberRepository memberRepository;
    private final ChallengeRepository challengeRepository;

    public RoutineResponse createRoutine(RoutineRequest.Create routineCreateRequest) {
        Routine entity = Routine.toEntity(routineCreateRequest);

        Member member = memberRepository.findById(routineCreateRequest.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        Challenge challenge =  challengeRepository.findById(routineCreateRequest.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 챌린지입니다."));

        entity.setMember(member);
        entity.setChallenge(challenge);

        routineRepository.save(entity);

        return RoutineResponse.from(entity);
    }

    public RoutineResponse getRoutine(Long id) {
        Routine entity = routineRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 루틴입니다."));

        return RoutineResponse.from(entity);
    }

    public void updateRoutine(Long id, RoutineRequest.Update routineUpdateRequest) {
        Routine entity = routineRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 루틴입니다."));

        entity.update(routineUpdateRequest);

        routineRepository.save(entity);
    }

    public void deleteRoutine(Long id) {
        Routine entity = routineRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 루틴입니다."));

        routineRepository.delete(entity);
    }
}
