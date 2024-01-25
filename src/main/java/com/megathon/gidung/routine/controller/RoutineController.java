package com.megathon.gidung.routine.controller;

import com.megathon.gidung.routine.dto.RoutineRequest;
import com.megathon.gidung.routine.dto.RoutineResponse;
import com.megathon.gidung.routine.service.RoutineService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/routines")
@RequiredArgsConstructor
public class RoutineController {

    private final RoutineService routineService;

    @PostMapping
    @Operation(summary="루틴 생성")
    public ResponseEntity createRoutine(
            @RequestBody RoutineRequest.Create routineCreateRequest
    ) {
        RoutineResponse response = routineService.createRoutine(routineCreateRequest);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @GetMapping("/members/{memberId}")
    public ResponseEntity getMemberRoutines(
            @PathVariable Long memberId
    ) {
        return new ResponseEntity(memberId, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary="루틴 조회")
    public ResponseEntity getRoutine(
            @PathVariable Long id
    ) {
        RoutineResponse response = routineService.getRoutine(id);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary="루틴 업데이트")
    public ResponseEntity updateRoutine(
            @PathVariable Long id,
            @RequestBody RoutineRequest.Update routineUpdateRequest
    ) {
        routineService.updateRoutine(id, routineUpdateRequest);
        return new ResponseEntity(null, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    @Operation(summary="루틴 삭제")
    public ResponseEntity deleteRoutine(
            @PathVariable Long id
    ) {
        routineService.deleteRoutine(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
