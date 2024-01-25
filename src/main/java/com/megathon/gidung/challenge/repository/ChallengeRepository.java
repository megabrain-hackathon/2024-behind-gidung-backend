package com.megathon.gidung.challenge.repository;

import com.megathon.gidung.challenge.entity.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {

    List<Challenge> findAllByIsVisibleTrue();
}
