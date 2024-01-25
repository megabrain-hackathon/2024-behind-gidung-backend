package com.megathon.gidung.todo.dto;

import com.megathon.gidung.member.dto.MemberResponse;
import com.megathon.gidung.todo.entity.Todo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TodoResponse {

    private Long id;

    private String title;

    private Boolean isDone;

    private String createdAt;

    private String updatedAt;

    private MemberResponse member;

    public static List<TodoResponse> toList(List<Todo> todos) {
        return todos.stream()
                .map(TodoResponse::from)
                .toList();
    }

    public static TodoResponse from(Todo todo) {
        TodoResponse response = new TodoResponse();
        response.setId(todo.getId());
        response.setTitle(todo.getTitle());
        response.setIsDone(todo.getIsDone());
        response.setCreatedAt(todo.getCreatedAt().toString());
        response.setUpdatedAt(todo.getUpdatedAt().toString());
        response.setMember(MemberResponse.from(todo.getMember()));
        return response;
    }
}
