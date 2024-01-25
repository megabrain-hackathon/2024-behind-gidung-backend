package com.megathon.gidung.challenge.dto;

import com.megathon.gidung.member.dto.MemberResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChallengeDetailResponse {

    private Long id;

    private String title;

    private String content;

    private String startAt;

    private String endAt;

    private Boolean isVisible;

    private MemberResponse member;

    private Long challengeMemberCount;

    private Long challengeMemberGoalCount;

    private String createdTime;

    private String updatedTime;

}
