package com.megathon.gidung.routine.dto;

import com.megathon.gidung.challenge.dto.ChallengeResponse;
import com.megathon.gidung.member.dto.MemberResponse;
import com.megathon.gidung.routine.entity.Routine;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RoutineResponse {

    private Long routineId;

    private ChallengeResponse challenge;

    private MemberResponse member;

    private String routineTitle;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;

    public static RoutineResponse from(Routine entity) {
        RoutineResponse routineResponse = new RoutineResponse();
        routineResponse.setRoutineId(entity.getRoutineId());
        routineResponse.setChallenge(ChallengeResponse.from(entity.getChallenge()));
        routineResponse.setMember(MemberResponse.from(entity.getMember()));
        routineResponse.setRoutineTitle(entity.getRoutineTitle());
        routineResponse.setCreatedTime(entity.getCreatedTime());
        routineResponse.setUpdatedTime(entity.getUpdatedTime());

        return routineResponse;
    }
}
