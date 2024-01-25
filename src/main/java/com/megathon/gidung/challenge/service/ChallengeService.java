package com.megathon.gidung.challenge.service;

import com.megathon.gidung.challenge.dto.ChallengeRequest;
import com.megathon.gidung.challenge.dto.ChallengeResponse;
import com.megathon.gidung.challenge.entity.Challenge;
import com.megathon.gidung.challenge.repository.ChallengeRepository;
import com.megathon.gidung.member.entity.Member;
import com.megathon.gidung.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChallengeService {

    private final ChallengeRepository challengeRepository;

    private final MemberRepository memberRepository;

    public void createChallenge(ChallengeRequest.Create challengeCreateRequest) {
        Challenge challenge = Challenge.toEntity(challengeCreateRequest);

        Member member = memberRepository.findById(challengeCreateRequest.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        challenge.setMember(member);

        challengeRepository.save(challenge);
    }

    public List<ChallengeResponse> getChallenges() {
        List<Challenge> challenges = challengeRepository.findAll();

        return ChallengeResponse.from(challenges);
    }

    public ChallengeResponse getChallenge(Long id) {
        Challenge challenge = challengeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 챌린지입니다."));

        return ChallengeResponse.from(challenge);
    }

    public void deleteChallenge(Long id) {
        challengeRepository.deleteById(id);
    }

    public void updateChallenge(Long id, ChallengeRequest.Update challengeCreateRequest) {
        Challenge challenge = challengeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 챌린지입니다."));

        challenge.update(challengeCreateRequest);

        challengeRepository.save(challenge);
    }
}
