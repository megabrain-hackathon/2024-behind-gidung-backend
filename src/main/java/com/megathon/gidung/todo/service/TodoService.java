package com.megathon.gidung.todo.service;

import com.megathon.gidung.member.entity.Member;
import com.megathon.gidung.member.repository.MemberRepository;
import com.megathon.gidung.todo.dto.TodoRequest;
import com.megathon.gidung.todo.dto.TodoResponse;
import com.megathon.gidung.todo.entity.Todo;
import com.megathon.gidung.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    private final MemberRepository memberRepository;

    public void createTodo(TodoRequest.Create todoCreateRequest) {
        Todo entity = Todo.toEntity(todoCreateRequest);

        Member member = memberRepository.findById(todoCreateRequest.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        entity.setMember(member);

        todoRepository.save(entity);
    }

    public List<TodoResponse> getTodosByMemberId(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        List<Todo> todos = todoRepository.findByMemberAndIsDoneFalse(member);

        return TodoResponse.toList(todos);
    }

    public TodoResponse updateTodo(Long id, TodoRequest.Update todoUpdateRequest) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 할 일입니다."));

        todo.update(todoUpdateRequest);

        todoRepository.save(todo);
        return TodoResponse.from(todo);
    }

    public void deleteTodoById(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 할 일입니다."));

        todoRepository.delete(todo);
    }

    public TodoResponse doneTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 할 일입니다."));

        todo.done();

        todoRepository.save(todo);
        return TodoResponse.from(todo);
    }
}
