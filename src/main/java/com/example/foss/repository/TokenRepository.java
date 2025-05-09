package com.example.foss.repository;

import com.example.foss.domain.Member;
import com.example.foss.domain.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token,Long> {
}
