package com.megathon.gidung.challenge.entity;

import com.megathon.gidung.challenge.dto.ChallengeCreateRequest;
import com.megathon.gidung.member.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "member")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", length = 50, nullable = false)
    private String title;

    @Column(name = "content", length = 500, nullable = false)
    private String content;

    @Column(name = "start_at", nullable = false)
    private LocalDate startAt;

    @Column(name = "end_at", nullable = false)
    private LocalDate endAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "created_time", nullable = false)
    private LocalDateTime createdTime;

    @Column(name = "updated_time", nullable = false)
    private LocalDateTime updatedTime;

    public static Challenge toEntity(ChallengeCreateRequest challengeCreateRequest) {
        return Challenge.builder()
                .title(challengeCreateRequest.getTitle())
                .content(challengeCreateRequest.getContent())
                .startAt(challengeCreateRequest.getStartAt())
                .endAt(challengeCreateRequest.getEndAt())
                .build();
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public void update(ChallengeCreateRequest challengeCreateRequest) {
        this.title = challengeCreateRequest.getTitle();
        this.content = challengeCreateRequest.getContent();
        this.startAt = challengeCreateRequest.getStartAt();
        this.endAt = challengeCreateRequest.getEndAt();
    }
}
