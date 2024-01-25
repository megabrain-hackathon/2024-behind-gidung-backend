package com.megathon.gidung.todo.controller;

import com.megathon.gidung.todo.dto.TodoRequest;
import com.megathon.gidung.todo.dto.TodoResponse;
import com.megathon.gidung.todo.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    @Operation(summary = "할 일 생성")
    public ResponseEntity createTodo(
            @RequestBody TodoRequest.Create todoCreateRequest
    ) {
        todoService.createTodo(todoCreateRequest);
        return new ResponseEntity(null, HttpStatus.CREATED);
    }

    @GetMapping("/members/{id}")
    @Operation(summary = "멤버의 할 일 조회")
    public ResponseEntity getTodos(
            @PathVariable Long id
    ) {
        return new ResponseEntity(todoService.getTodosByMemberId(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "할 일 수정")
    public ResponseEntity updateTodo(
            @PathVariable Long id,
            @RequestBody TodoRequest.Update todoUpdateRequest
    ) {
        TodoResponse response = todoService.updateTodo(id, todoUpdateRequest);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PutMapping("/{id}/done")
    @Operation(summary = "할 일 완료")
    public ResponseEntity updateTodo(
            @PathVariable Long id
    ) {
        TodoResponse response = todoService.doneTodo(id);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "할 일 삭제")
    public ResponseEntity deleteTodo(
            @PathVariable Long id
    ) {
        todoService.deleteTodoById(id);
        return new ResponseEntity(null, HttpStatus.NO_CONTENT);
    }
}
