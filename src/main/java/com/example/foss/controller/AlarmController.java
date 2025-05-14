package com.example.foss.controller;

import com.example.foss.dto.ApiResponseDto;
import com.example.foss.dto.PostNotificationDto;
import com.example.foss.service.AlarmService;
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
public class AlarmController {
    private final AlarmService service;

    // 알림 등록
    @PostMapping("/alarm")
    public ResponseEntity<ApiResponseDto> postNotification(@RequestBody PostNotificationDto postNotificationDto, Principal principal) {
        return service.createNotification(postNotificationDto, principal);
    }
}
