package com.megathon.gidung.routine.dto;

import com.megathon.gidung.challenge.dto.ChallengeResponse;
import com.megathon.gidung.member.dto.MemberResponse;
import com.megathon.gidung.routine.entity.Routine;
import com.megathon.gidung.routine.entity.RoutineHistory;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class RoutineHistoryResponse {

    private Long routineHistoryId;

    private RoutineResponse routine;

    private LocalDate routineDate;

    private boolean isGoal;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;

    public static RoutineHistoryResponse from(RoutineHistory entity) {
        RoutineHistoryResponse routineResponse = new RoutineHistoryResponse();
        routineResponse.setRoutineHistoryId(entity.getRoutineHistoryId());
        routineResponse.setRoutine(RoutineResponse.from(entity.getRoutine()));
        routineResponse.setRoutineDate(entity.getRoutineDate());
        routineResponse.setGoal(entity.isGoal());
        routineResponse.setCreatedTime(entity.getCreatedTime());
        routineResponse.setUpdatedTime(entity.getUpdatedTime());

        return routineResponse;
    }
}
