package com.example.foss.repository;

import com.example.foss.domain.Token;
import com.example.foss.domain.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic,Long> {
}
