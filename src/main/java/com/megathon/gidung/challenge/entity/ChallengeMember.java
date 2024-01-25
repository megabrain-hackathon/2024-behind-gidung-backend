package com.megathon.gidung.challenge.entity;

import com.megathon.gidung.challenge.dto.ChallengeMemberRequest;
import com.megathon.gidung.challenge.dto.ChallengeRequest;
import com.megathon.gidung.member.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "challenge_member")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChallengeMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "challenge_member_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "is_goal")
    private Boolean isGoal;

    @Column(name = "created_time")
    private LocalDateTime createdTime;


    public static ChallengeMember toEntity(Challenge challenge, Member member) {
        return ChallengeMember.builder()
                .challenge(challenge)
                .member(member)
                .isGoal(false)
                .createdTime(LocalDateTime.now())
                .build();
    }
}
