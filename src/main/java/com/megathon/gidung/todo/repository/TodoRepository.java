package com.megathon.gidung.todo.repository;

import com.megathon.gidung.member.entity.Member;
import com.megathon.gidung.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByMemberAndIsDoneFalse(Member member);


}
