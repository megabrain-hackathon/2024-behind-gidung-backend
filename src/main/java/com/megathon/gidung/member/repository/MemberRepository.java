package com.megathon.gidung.member.repository;

import com.megathon.gidung.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
