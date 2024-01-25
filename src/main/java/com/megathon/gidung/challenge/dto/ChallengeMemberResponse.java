package com.megathon.gidung.challenge.dto;

import com.megathon.gidung.challenge.entity.ChallengeMember;
import com.megathon.gidung.member.dto.MemberResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ChallengeMemberResponse {

    private ChallengeResponse challenge;

    private MemberResponse member;

    private Boolean isGoal;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")

    private LocalDateTime createdTime;

    public static ChallengeMemberResponse from(ChallengeMember challengeMember) {
        ChallengeMemberResponse challengeMemberResponse = new ChallengeMemberResponse();
        challengeMemberResponse.setChallenge(ChallengeResponse.from(challengeMember.getChallenge()));
        challengeMemberResponse.setMember(MemberResponse.from(challengeMember.getMember()));
        challengeMemberResponse.setIsGoal(challengeMember.getIsGoal());
        challengeMemberResponse.setCreatedTime(challengeMember.getCreatedTime());
        return challengeMemberResponse;
    }

    public static List<ChallengeMemberResponse> toList(List<ChallengeMember> challengeMembers) {
        return challengeMembers.stream()
                .map(ChallengeMemberResponse::from)
                .toList();
    }
}
