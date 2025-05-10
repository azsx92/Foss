package com.example.foss.service;

import com.example.foss.repository.AlarmRepository;
import com.example.foss.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AlarmService {

    private final AlarmRepository alarmRepository;
    private final MemberRepository memberRepository;
    private final FCMService fcmService;
}
