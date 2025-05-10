package com.example.foss.repository;

import com.example.foss.domain.Token;
import com.example.foss.domain.Topic;
import com.example.foss.domain.TopicMember;
import com.example.foss.domain.TopicToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TopicTokenRepository extends JpaRepository<TopicToken, Long> {
    void deleteByTopic(Topic topic);

    List<TopicToken> findByToken(Token token);

    // 토큰 리스트를 기반으로 TopicToken 객체들을 조회
    List<TopicToken> findByTokenIn(List<Token> tokens);

    void deleteByTokenIn (List<Token> tokens);
    @Transactional
    @Modifying
    @Query("DELETE FROM TopicToken tt where tt.token.tokenValue In : tokenValues")
    void deleteByTokenValueIn(@Param("tokenValues") List<String> tokenValues);
}
