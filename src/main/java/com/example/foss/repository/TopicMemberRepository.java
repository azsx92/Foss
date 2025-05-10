package com.example.foss.repository;

import com.example.foss.domain.Member;
import com.example.foss.domain.Topic;
import com.example.foss.domain.TopicMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicMemberRepository extends JpaRepository<TopicMember,Long> {
    List<TopicMember> findByMember(Member member);

    void deleteByMember(Member member);

    boolean existsByTopicAndMember(Topic topic, Member member);

    // Member 와 Topic으로 TopicMember 삭제
    void deleteByTopicAndMember(Topic topic, Member member);

    List<TopicMember> findByTopic(Topic topic);
}
