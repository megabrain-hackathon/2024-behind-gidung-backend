package com.megathon.gidung.routine.entity;

import com.megathon.gidung.challenge.entity.Challenge;
import com.megathon.gidung.member.entity.Member;
import com.megathon.gidung.routine.dto.RoutineRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "routine")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Routine {

    @Id
    @Column(name = "routine_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long routineId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "routine_title", length = 50)
    private String routineTitle;

    @Column(name = "created_time")
    private LocalDateTime createdTime;

    @Column(name = "updated_time")
    private LocalDateTime updatedTime;

    public static Routine toEntity(RoutineRequest.Create request) {
        return Routine.builder()
                .routineTitle(request.getRoutineTitle())
                .createdTime(LocalDateTime.now())
                .updatedTime(LocalDateTime.now())
                .build();
    }

    public void setMember(Member member) { this.member = member; }
    public void setChallenge(Challenge challenge) { this.challenge = challenge; }

    public void update(RoutineRequest.Update update){
        this.routineTitle = update.getRoutineTitle();
        this.updatedTime = LocalDateTime.now();
    }
}
