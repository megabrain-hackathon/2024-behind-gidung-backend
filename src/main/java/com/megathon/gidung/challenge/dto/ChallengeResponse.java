package com.megathon.gidung.challenge.dto;

import com.megathon.gidung.challenge.entity.Challenge;
import com.megathon.gidung.member.dto.MemberResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChallengeResponse {

    private Long id;

    private String title;

    private String content;

    private String startAt;

    private String endAt;

    private MemberResponse member;

    private String createdTime;

    private String updatedTime;

    public static ChallengeResponse from(Challenge challenge) {
        ChallengeResponse challengeResponse = new ChallengeResponse();

        challengeResponse.setId(challenge.getId());
        challengeResponse.setTitle(challenge.getTitle());
        challengeResponse.setContent(challenge.getContent());
        challengeResponse.setStartAt(challenge.getStartAt().toString());
        challengeResponse.setEndAt(challenge.getEndAt().toString());
        challengeResponse.setMember(MemberResponse.from(challenge.getMember()));
        challengeResponse.setCreatedTime(challenge.getCreatedTime().toString());
        challengeResponse.setUpdatedTime(challenge.getUpdatedTime().toString());

        return challengeResponse;
    }

    public static List<ChallengeResponse> from(List<Challenge> challenges) {
        return challenges
                .stream()
                .map(ChallengeResponse::from)
                .toList();
    }
}
