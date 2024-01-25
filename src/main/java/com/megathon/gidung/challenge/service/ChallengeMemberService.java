package com.megathon.gidung.challenge.service;

import com.megathon.gidung.challenge.dto.ChallengeMemberRequest;
import com.megathon.gidung.challenge.entity.Challenge;
import com.megathon.gidung.challenge.entity.ChallengeMember;
import com.megathon.gidung.challenge.repository.ChallengeMemberRepository;
import com.megathon.gidung.challenge.repository.ChallengeRepository;
import com.megathon.gidung.member.entity.Member;
import com.megathon.gidung.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChallengeMemberService {

    private final ChallengeMemberRepository challengeMemberRepository;

    private final ChallengeRepository challengeRepository;

    private final MemberRepository memberRepository;

    public void createChallengeMember(ChallengeMemberRequest.Create challengeMemberCreateRequest) {

        Member member = memberRepository.findById(challengeMemberCreateRequest.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        Challenge challenge = challengeRepository.findById(challengeMemberCreateRequest.getChallengeId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 챌린지입니다."));

        challengeMemberRepository.findByChallengeAndMember(challenge, member)
                .ifPresent(challengeMember -> {
                    throw new IllegalArgumentException("이미 도전한 챌린지입니다.");
                });

        ChallengeMember entity = ChallengeMember.toEntity(challenge, member);
        challengeMemberRepository.save(entity);
    }

    public void deleteChallengeMember(ChallengeMemberRequest.Create challengeMemberCreateRequest) {

        Challenge challenge = challengeRepository.findById(challengeMemberCreateRequest.getChallengeId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 챌린지입니다."));

        Member member = memberRepository.findById(challengeMemberCreateRequest.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        ChallengeMember challengeMember = challengeMemberRepository.findByChallengeAndMember(challenge, member)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 챌린지 도전자입니다."));

        challengeMemberRepository.delete(challengeMember);
    }
}
