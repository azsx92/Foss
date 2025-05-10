package com.example.foss.repository;

import com.example.foss.domain.Member;
import com.example.foss.domain.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findByTokenValueAndMember(String value, Member member);

    @Modifying
    @Query("DELETE FROM Token t WHERE t IN : failedTokens")
    void deleteByTokenIn(@Param("failedTokens")List<Token> failedTokens);

    @Modifying
    @Query("DELETE FROM Token t WHERE t.tokenValue IN : failedTokens")
    void deleteByTokenValueIn(@Param("failedTokens") List<String> failedTokens);

    List<Token> findByExpirationDate(LocalDate now);
}
