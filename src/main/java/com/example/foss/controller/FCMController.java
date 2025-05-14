package com.example.foss.controller;

import com.example.foss.dto.ApiResponseDto;
import com.example.foss.dto.PostNotificationDto;
import com.example.foss.dto.TokenNotificationRequestDto;
import com.example.foss.dto.TopicNotificationRequestDto;
import com.example.foss.service.AlarmService;
import com.example.foss.service.FCMService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FCMController {
    private final FCMService service;

    // fcm을 보낸다 (token)
    @PostMapping("/notification/token")
    public ResponseEntity<ApiResponseDto> sendMessageToken(@RequestBody TokenNotificationRequestDto tokenNotificationRequestDto, Principal principal) {
        return service.sendByToken(tokenNotificationRequestDto, principal);
    }

    // fcm을 보낸다 (topic)
    @PostMapping("/notification/topic")
    public ResponseEntity<ApiResponseDto> sendMessageTopic(@RequestBody TopicNotificationRequestDto topicNotificationRequestDto) {
        return service.sendByTopic(topicNotificationRequestDto);
    }
}
