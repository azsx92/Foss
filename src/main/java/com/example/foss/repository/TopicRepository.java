package com.example.foss.repository;

import com.example.foss.domain.Token;
import com.example.foss.domain.Topic;
import com.example.foss.domain.TopicMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TopicRepository extends JpaRepository<Topic,Long> {

    Optional<Topic> findByTopicName(String topicName);
}
