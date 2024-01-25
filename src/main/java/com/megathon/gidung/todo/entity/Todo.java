package com.megathon.gidung.todo.entity;

import com.megathon.gidung.member.entity.Member;
import com.megathon.gidung.todo.dto.TodoRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "todo")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Todo {

    @Id
    @Column(name = "todo_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "is_done")
    private Boolean isDone;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public static Todo toEntity(TodoRequest.Create todoCreateRequest) {
        return Todo.builder()
                .title(todoCreateRequest.getTitle())
                .isDone(false)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public void update(TodoRequest.Update todoUpdateRequest) {
        this.title = todoUpdateRequest.getTitle();
        this.updatedAt = LocalDateTime.now();
    }

    public void done() {
        this.isDone = true;
        this.updatedAt = LocalDateTime.now();
    }
}
