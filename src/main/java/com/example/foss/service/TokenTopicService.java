package com.example.foss.service;

import com.example.foss.auth.JwtUtil;
import com.example.foss.domain.*;
import com.example.foss.dto.*;
import com.example.foss.exception.CustomErrorCode;
import com.example.foss.exception.CustomException;
import com.example.foss.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TokenTopicService {
    private final TokenRepository tokenRepository;
    private final TopicRepository topicRepository;
    private final TopicTokenRepository topicTokenRepository;
    private final TopicMemberRepository topicMemberRepository;
    private final MemberRepository memberRepository ;
    private final FCMService fcmService;

    // 토큰 만료 기간 상수 정의
    final int TOKEN_EXPIRATION_MONTHS = 2;

    // 토픽 구독 - 토픽 하나씩
    @Transactional
    public void subscribeToTopics(TopicRequest topicRequest, Principal principal) {
        String topicName = topicRequest.getTopic();

        // 토픽 가져오기 또는 에러처리
        Topic topic = topicRepository.findByTopicName(topicName)
                .orElseThrow(() -> new CustomException(CustomErrorCode.TOPIC_NOT_FOUND));

        // 사용자 정보는 스프링시큐리티 컨택스티에서 가져옴
        String memberEmail = principal.getName();

        // 현재 사용자 정보 가져오기
        Member member = memberRepository.findByEmail(memberEmail)
                .orElseThrow(() -> new CustomException(CustomErrorCode.TOPIC_NOT_FOUND));

        // 이미 해당 토픽에 구독 중인지 확인
        if (topicMemberRepository.existsByTopicAndMember(topic, member)) {
            throw new CustomException(CustomErrorCode.ALREADY_SUBSCRIBED_TOPIC);
        }

        // 현재 사용자의 토큰 목록 가져오기
        List<Token> memberTokens = member.getTokens();

        // TopicMember 생성 후 Repository에 저장
        TopicMember topicMember = TopicMember.builder()
                .topic(topic)
                .member(member)
                .build();

        topicMemberRepository.save(topicMember);

        // 토피과 토큰을 매핑하여 저장 -> 사용자가 가지고 있는 토큰들이 topic을 구독
        List<TopicToken> topicTokens  = memberTokens.stream()
                .map(token -> new TopicToken(topic, token))
                .collect(Collectors.toList());
        topicTokenRepository.saveAll(topicTokens);

        // FCM 서비스를  사용하여 토픽에 대한 구독 진행
        List<String> tokenValues = memberTokens.stream()
                .map(Token::getTokenValue)
                .collect(Collectors.toList());
        fcmService.subscribeToTopic(topicName, tokenValues);
    }

    // 토픽 구독 취소 - 하나씩
    @Transactional
    public void unsubscribeFromTopics(TopicRequest topicRequest, Principal principal) {
        String topicName = topicRequest.getTopic();

        // 토픽 가져오기 또는 에러처리
        Topic topic = topicRepository.findByTopicName(topicName)
                .orElseThrow(() -> new CustomException(CustomErrorCode.TOPIC_NOT_FOUND));

        // 사용자 정보는 스프링 시큐리티 컨텍스트에서 가져옴
        String memberEmail = principal.getName();

        // 현재 사용자 정보 가져오기
        Member member = memberRepository.findByEmail(memberEmail)
                .orElseThrow(() -> new CustomException(CustomErrorCode.USER_NOT_FOUND));

        // 멤버가 구독하고 잇는 해당 토픽을 찾아서 삭제
        topicMemberRepository.deleteByTopicAndMember(topic, member);

        // 해당 토픽을 구독하는 모든 TopicToken 삭제
        topicTokenRepository.deleteByTopic(topic);

        // 현재 사용자의 토큰 목록 가져오기
        List<Token> memberTokens = member.getTokens();
        // FCM 서비스를 사용하여 토픽에 대한 구독 취소 진행
        List<String> tokenValues = memberTokens.stream()
                .map(Token::getTokenValue)
                .collect(Collectors.toList());
        log.info(topicName + "구독이 취소되었습니다.");
        fcmService.unsubscribeToTopic(topicName, tokenValues);
    }

    public void saveFCMToken(MemberDto.LoginRequest loginRequest) {
        log.info("saveFCMToken 메서드 호출");

        // 현재 사용자 정보 가져오기
        Member member = memberRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new CustomException(CustomErrorCode.USER_NOT_FOUND));

        // token 이 이미 있는지 체크
        Optional<Token> existingToken = tokenRepository.findByTokenValueAndMember(loginRequest.getFcmToken(), member);
        if (existingToken.isPresent()) {
            Token token = existingToken.get();
            log.info("이미 존재하는 토큰: " + existingToken.get().getTopicTokens());
            token.setExpirationDate(LocalDate.now().plusMonths(2));
            tokenRepository.save(token);
        } else {
            // Only create and save a new token if it does not exist
            Token token = Token.builder()
                    .tokenValue(loginRequest.getFcmToken())
                    .member(member)
                    .expirationDate(LocalDate.now().plusMonths(TOKEN_EXPIRATION_MONTHS))
                    .build();
            log.info("DB에 저장하는 token : " + token.getTokenValue());
            tokenRepository.save(token);

            // 사용자가 구독 중인 모든 토픽을 가져옴
            List<TopicMember> topicMembers = topicMemberRepository.findByMember(member);
            List<Topic> subscribedTopics = topicMembers.stream()
                    .map(TopicMember::getTopic)
                    .distinct()
                    .collect(Collectors.toList());

            // 새 토큰을 기존에 구독된 모든 토픽과 매핑하여 TopicToken 생성 및 저장
            List<TopicToken> newSubscriptions = subscribedTopics.stream()
                    .map(topic -> new TopicToken(topic, token))
                    .collect(Collectors.toList());
            topicTokenRepository.saveAll(newSubscriptions);

            // 각 토픽에 대해 새 토큰 구독 처리
            for (Topic topic : subscribedTopics) {
                fcmService.subscribeToTopic(topic.getTokenName(), Collections.singletonList(token.getTokenValue()));
               log.info("새 토큰으로 " + topic.getTokenName() + " 토픽을 다시 구독합니다.");
            }
            
        }
    }

    // 매일 00: 00(자정) 에 트리거됩니다.(0 0 0 ** ?). 따라서 하루에 한 번 작업이 실행됩니다.
    // 매일 자정에 실행되는 스케줄링 작업
    //@Scheduled(cron = "0 0 0 * * ?")
    @Transactional
    public void unsubscribeExpiredTokens(){
        LocalDate now = LocalDate.now();
        log.info("오늘의 날짜 :" + now);

        // 만료된 토큰을 가져옵니다.
        List<Token> expiredTokens = tokenRepository.findByExpirationDate(now);

        // 만료된 토큰과 관련된 모든 TopicToken을 찾음
        List<TopicToken> topicTokens = topicTokenRepository.findByTokenIn(expiredTokens);

        // 만료된 토큰의 값들을 추출
        List<String> tokenValues = expiredTokens.stream()
                .map(Token::getTokenValue)
                .collect(Collectors.toList());

        // 각 TopicToken에 대홰 구독 해지
        topicTokens.forEach(topicToken -> {
            fcmService.unsubscribeToTopic(topicToken.getTopic().getTokenName(), tokenValues);
        });

        // 만료된 토큰 삭제
        topicTokenRepository.deleteAll(topicTokens); // TopicTokenRepository 에서 먼저 삭제하고 TokenRepository에서 삭제
        tokenRepository.deleteAll(expiredTokens);
    }
}
