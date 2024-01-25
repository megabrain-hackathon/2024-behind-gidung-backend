package com.megathon.gidung.routine.controller;

import com.megathon.gidung.routine.dto.RoutineHistoryRequest;
import com.megathon.gidung.routine.dto.RoutineHistoryResponse;
import com.megathon.gidung.routine.service.RoutineHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/routineHistory")
@RequiredArgsConstructor
public class RoutineHistoryController {

    private final RoutineHistoryService routineHistoryService;

    @PostMapping
    @Operation(summary="루틴 히스토리 생성")
    public ResponseEntity createRoutineHistory(
            @RequestBody RoutineHistoryRequest.Create routineCreateRequest
    ) {
        RoutineHistoryResponse response = routineHistoryService.createRoutineHistory(routineCreateRequest);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary="루틴 히스토리 조회")
    public ResponseEntity getRoutineHistory(
            @PathVariable Long id
    ) {
        RoutineHistoryResponse response = routineHistoryService.getRoutineHistory(id);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary="루틴 히스토리 업데이트")
    public ResponseEntity updateRoutineHistory(
            @PathVariable Long id,
            @RequestBody RoutineHistoryRequest.Update routineUpdateRequest
    ) {
        routineHistoryService.updateRoutineHistory(id, routineUpdateRequest);
        return new ResponseEntity(null, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    @Operation(summary="루틴 히스토리 삭제")
    public ResponseEntity deleteRoutineHistory(
            @PathVariable Long id
    ) {
        routineHistoryService.deleteRoutineHistory(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
