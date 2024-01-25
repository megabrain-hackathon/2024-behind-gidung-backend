package com.megathon.gidung.routine.service;

import com.megathon.gidung.routine.dto.RoutineHistoryRequest;
import com.megathon.gidung.routine.dto.RoutineHistoryResponse;
import com.megathon.gidung.routine.entity.Routine;
import com.megathon.gidung.routine.entity.RoutineHistory;
import com.megathon.gidung.routine.repository.RoutineHistoryRepository;
import com.megathon.gidung.routine.repository.RoutineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoutineHistoryService {

    private final RoutineHistoryRepository routineHistoryRepository;
    private final RoutineRepository routineRepository;

    public RoutineHistoryResponse createRoutineHistory(RoutineHistoryRequest.Create routineCreateRequest) {
        RoutineHistory entity = RoutineHistory.toEntity(routineCreateRequest);

        Routine routine = routineRepository.findById(routineCreateRequest.getRoutineId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 루틴입니다."));

        entity.setRoutine(routine);

        routineHistoryRepository.save(entity);

        return RoutineHistoryResponse.from(entity);
    }

    public RoutineHistoryResponse getRoutineHistory(Long id) {
        RoutineHistory entity = routineHistoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 루틴 기록입니다."));

        return RoutineHistoryResponse.from(entity);
    }

    public void updateRoutineHistory(Long id, RoutineHistoryRequest.Update routineUpdateRequest) {
        RoutineHistory entity = routineHistoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 루틴 기록입니다."));

        entity.update(routineUpdateRequest);

        routineHistoryRepository.save(entity);
    }

    public void deleteRoutineHistory(Long id) {
        RoutineHistory entity = routineHistoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 루틴 기록입니다."));

        routineHistoryRepository.delete(entity);
    }
}
