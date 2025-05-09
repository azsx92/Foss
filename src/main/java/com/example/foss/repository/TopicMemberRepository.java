package com.example.foss.repository;

import com.example.foss.domain.Topic;
import com.example.foss.domain.TopicMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicMemberRepository extends JpaRepository<TopicMember,Long> {
}
