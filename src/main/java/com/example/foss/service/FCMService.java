package com.example.foss.service;

import com.example.foss.domain.Member;
import com.example.foss.domain.Token;
import com.example.foss.dto.AlarmDto;
import com.example.foss.dto.ApiResponseDto;
import com.example.foss.dto.TokenNotificationRequestDto;
import com.example.foss.exception.CustomErrorCode;
import com.example.foss.exception.CustomException;
import com.example.foss.repository.MemberRepository;
import com.example.foss.repository.TokenRepository;
import com.example.foss.repository.TopicTokenRepository;
import com.google.firebase.messaging.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
@Slf4j
public class FCMService {

    private final TokenRepository tokenRepository;
    private final MemberRepository memberRepository;
    private final TopicTokenRepository topicTokenRepository;

    @Transactional
    public void sendByAlarm(AlarmDto alarmDto) {
        // 사용자 조회
        Member member = memberRepository.findByEmail(alarmDto.getEmail())
                .orElseThrow(() -> new CustomException(CustomErrorCode.USER_NOT_FOUND));

        List<Token> tokens = member.getTokens();

        // 실패한 토큰 리스트 초기화
        List<Token> failedTokens = new ArrayList<>();

        for (Token token : tokens) {
            Message message = Message.builder()
                    .setToken(token.getTokenValue())
                    .setNotification(Notification.builder()
                            .setTitle(alarmDto.getTitle())
                            .setBody(alarmDto.getContent())
                            .setImage(alarmDto.getImg())
                            .build())
                    .putData("click_action", alarmDto.getUrl())
                    .build();
            try {
                FirebaseMessaging.getInstance().send(message);
            } catch (FirebaseMessagingException e) {
                failedTokens.add(token);
                log.error("Failed to send notification to token: " + token.getTokenValue(), e);
            }
        }

        if (!failedTokens.isEmpty()) {
            log.warn("유효하지 않은 토큰 목록 : " + failedTokens);
            tokenRepository.deleteByTokenIn(failedTokens);
            topicTokenRepository.deleteByTokenIn(failedTokens);
        }

        ResponseEntity.ok().body(ApiResponseDto.builder()
                .successStatus(HttpStatus.OK)
                .successContent("푸시 알림 성공")
                .date(tokens)
                .build());
    }


    @Transactional
    public ResponseEntity<ApiResponseDto> sendByToken(TokenNotificationRequestDto tokenNotificationRequestDto, Principal principal) {
        String email = principal.getName();
        // 사용자 조회
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(CustomErrorCode.USER_NOT_FOUND));

        List<Token> tokens = member.getTokens();

        // 실패한 토큰 리스트 초기화
        List<Token> failedTokens = new ArrayList<>();

        for (Token token : tokens) {
            log.info("메시지 보내는 토큰 " + token.toString());
            Message message = Message.builder()
                    .setToken(token.getTokenValue())
                    .setNotification(Notification.builder()
                            .setTitle(tokenNotificationRequestDto.getTitle())
                            .setBody(tokenNotificationRequestDto.getContent())
                            .setImage(tokenNotificationRequestDto.getImg())
                            .build())
                    .putData("click_action", tokenNotificationRequestDto.getUrl())
                    .build();
            try {
                FirebaseMessaging.getInstance().send(message);
            } catch (FirebaseMessagingException e) {
                failedTokens.add(token);
                log.error("알림 전송에 실패한 token: " + token.getTokenValue(), e);
            }
        }

            return ResponseEntity.ok().body(ApiResponseDto.builder()
                    .successStatus(HttpStatus.OK)
                    .successContent("푸시 알림 성공")
                    .build());
        }

    public void subscribeToTopic(String topicName, List<String> tokens) {
        List<String> failedTokens = new ArrayList<>();

        try {
            TopicManagementResponse response = FirebaseMessaging.getInstance().
                    subscribeToTopicAsync(tokens, topicName).get();
                    log.info("구독하는 topic: " + topicName);
                    log.info(response.getSuccessCount() + "개의 토큰이 구독에 성공했습니다.");
            if (response.getFailureCount() > 0) {
                log.info(response.getFailureCount() + "개의 토큰이 구독을 실패했습니다.");
                response.getErrors().forEach(error -> {
                    failedTokens.add(tokens.get(error.getIndex()));
                    log.info("Error for token at index " + error.getIndex() + ": " + error.getReason()
                    + "(Token: " + failedTokens.get(error.getIndex()-1) + ")");
                });
            }
        } catch (InterruptedException | ExecutionException exception) {
            throw new CustomException(CustomErrorCode.SUBSCRIBE_FAILED);
        }

        if (!failedTokens.isEmpty()) {
            log.warn("구독에 실패한 토큰 입니다. : " + failedTokens);
            topicTokenRepository.deleteByTokenValueIn(failedTokens);
            tokenRepository.deleteByTokenValueIn(failedTokens);
        }
    }

    public void unsubscribeToTopic(String topic, List<String> tokens) {
        List<String> failedTokens = new ArrayList<>();

        try {
            TopicManagementResponse response = FirebaseMessaging.getInstance().
                    unsubscribeFromTopicAsync(tokens, topic).get();
            log.info("구독 취소하는 topic: " + topic);
            log.info(response.getSuccessCount() + "개의 토큰이 구독 취소에 성공했습니다.");
            if (response.getFailureCount() > 0) {
                log.info(response.getFailureCount() + "개의 토큰이 구독 취소를 실패했습니다.");
                response.getErrors().forEach(error -> {
                    failedTokens.add(tokens.get(error.getIndex()));
                    log.info("Error for token at index " + error.getIndex() + ": " + error.getReason()
                            + "(Token: " + failedTokens.get(error.getIndex()-1) + ")");
                });
            }
        } catch (InterruptedException | ExecutionException exception) {
            throw new CustomException(CustomErrorCode.SUBSCRIBE_CANCEL_FAILED);
        }

        if (!failedTokens.isEmpty()) {
            log.warn("구독 취소에 실패한 토큰 입니다. : " + failedTokens);
            tokenRepository.deleteByTokenValueIn(failedTokens);
        }
    }
}

