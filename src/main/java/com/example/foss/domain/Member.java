package com.example.foss.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;


    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private String password;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,
            CascadeType.REMOVE}, orphanRemoval = true)
    private List<Token> tokens;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,
            CascadeType.REMOVE}, orphanRemoval = true)
    private List<TopicMember> topicMembers;

    @Builder
    public Member(Long id, String email, String name, String password, String major, String phone) {
        this.id = id;
        this.email = email;
        this.password = password;
    }
}
