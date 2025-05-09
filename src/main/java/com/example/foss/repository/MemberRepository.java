package com.example.foss.repository;

import com.example.foss.domain.Alarm;
import com.example.foss.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
}
