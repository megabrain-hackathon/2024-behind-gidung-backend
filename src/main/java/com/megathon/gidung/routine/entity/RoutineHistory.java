package com.megathon.gidung.routine.entity;

import com.megathon.gidung.challenge.entity.Challenge;
import com.megathon.gidung.member.entity.Member;
import com.megathon.gidung.routine.dto.RoutineHistoryRequest;
import com.megathon.gidung.routine.dto.RoutineRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "routine_history")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoutineHistory {

    @Id
    @Column(name = "routine_history_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long routineHistoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "routine_id")
    private Routine routine;

    @Column(name = "routine_date")
    private LocalDate routineDate;

    @Column(name = "is_goal")
    private boolean isGoal;

    @Column(name = "created_time")
    private LocalDateTime createdTime;

    @Column(name = "updated_time")
    private LocalDateTime updatedTime;

    public static RoutineHistory toEntity(RoutineHistoryRequest.Create request) {
        return RoutineHistory.builder()
                .isGoal(false)
                .routineDate(LocalDate.now())
                .createdTime(LocalDateTime.now())
                .updatedTime(LocalDateTime.now())
                .build();
    }

    public void setRoutine(Routine routine) { this.routine = routine; }

    public void update(RoutineHistoryRequest.Update update){
        this.isGoal = update.isGoal();
        this.updatedTime = LocalDateTime.now();
    }
}
