package com.megathon.gidung.member.repository;

import com.megathon.gidung.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByUsername(String username);

    Optional<Member> findByUsernameAndPassword(String username, String password);
}
