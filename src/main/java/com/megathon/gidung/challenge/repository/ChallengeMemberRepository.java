package com.megathon.gidung.challenge.repository;

import com.megathon.gidung.challenge.entity.Challenge;
import com.megathon.gidung.challenge.entity.ChallengeMember;
import com.megathon.gidung.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChallengeMemberRepository extends JpaRepository<ChallengeMember, Long> {
    List<ChallengeMember> findByChallenge(Challenge challenge);

    Optional<ChallengeMember> findByChallengeAndMember(Challenge challenge, Member member);

    Long countByChallenge(Challenge challenge);
}
