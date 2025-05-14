package com.example.foss.controller;

import com.example.foss.dto.*;
import com.example.foss.service.MemberService;
import com.example.foss.service.TokenTopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/topic")
public class TopicController {

    private final TokenTopicService topicService;


    @PostMapping("/subscribe")
    public ResponseEntity<ApiResponseDto> subscribeToTopic(@RequestBody TopicRequest topicRequest, Principal principal) throws IOException {
        topicService.subscribeToTopics(topicRequest, principal);
        return ResponseEntity.ok().body(ApiResponseDto.builder()
                .successStatus(HttpStatus.OK)
                .successContent(topicRequest.getTopic() + " 토픽을 구독합니다.")
                .build()
        );
    }

    @PostMapping("/unsubscribe")
    public ResponseEntity<ApiResponseDto> unsubscribeToTopic(@RequestBody TopicRequest topicRequest, Principal principal) throws IOException {
        topicService.unsubscribeFromTopics(topicRequest, principal);
        return ResponseEntity.ok().body(ApiResponseDto.builder()
                .successStatus(HttpStatus.OK)
                .successContent(topicRequest.getTopic() + " 토픽을 구독을 취소합니다. ")
                .build()
        );
    }
}
