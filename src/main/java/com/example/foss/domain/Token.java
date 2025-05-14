package com.example.foss.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;


    @Column(unique = true)
    private String tokenValue;

    @Column(nullable = false)
    private LocalDate expirationDate;

    @OneToMany(mappedBy = "token")
    private List<TopicToken> topicTokens;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
