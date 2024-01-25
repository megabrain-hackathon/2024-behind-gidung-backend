package com.megathon.gidung.challenge.service;

import com.megathon.gidung.challenge.dto.ChallengeRequest;
import com.megathon.gidung.challenge.dto.ChallengeResponse;
import com.megathon.gidung.challenge.entity.Challenge;
import com.megathon.gidung.challenge.repository.ChallengeMemberRepository;
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

    private final ChallengeMemberRepository challengeMemberRepository;

    private final MemberRepository memberRepository;

    public void createChallenge(ChallengeRequest.Create challengeCreateRequest) {
        Challenge challenge = Challenge.toEntity(challengeCreateRequest);

        Member member = memberRepository.findById(challengeCreateRequest.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        challenge.setMember(member);

        challengeRepository.save(challenge);
    }

    public List<ChallengeResponse> getChallengesByVisible() {
        List<Challenge> challenges = challengeRepository.findAllByIsVisibleTrue();

        return ChallengeResponse.from(challenges);
    }

    public ChallengeResponse getChallenge(Long id) {
        Challenge challenge = challengeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 챌린지입니다."));

        return ChallengeResponse.from(challenge);
    }

    public void deleteChallenge(Long id) {
        Challenge challenge = challengeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 챌린지입니다."));
        validateChallenge(challenge, "삭제");

        challengeRepository.delete(challenge);
    }

    public void updateChallenge(Long id, ChallengeRequest.Update challengeCreateRequest) {
        Challenge challenge = challengeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 챌린지입니다."));
        validateChallenge(challenge, "수정");

        challenge.update(challengeCreateRequest);

        challengeRepository.save(challenge);
    }

    void validateChallenge(Challenge challenge, String method) {
        if(challengeMemberRepository.countByChallenge(challenge) > 0){
            throw new IllegalArgumentException("이미 참여한 회원이 있는 챌린지는 " + method + "할 수 없습니다.");
        }
    }

}
