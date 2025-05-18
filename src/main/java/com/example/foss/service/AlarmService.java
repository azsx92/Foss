package com.example.foss.service;

import com.example.foss.domain.Alarm;
import com.example.foss.domain.Member;
import com.example.foss.dto.AlarmDto;
import com.example.foss.dto.ApiResponseDto;
import com.example.foss.dto.PostNotificationDto;
import com.example.foss.exception.CustomErrorCode;
import com.example.foss.exception.CustomException;
import com.example.foss.repository.AlarmRepository;
import com.example.foss.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AlarmService {

    private final AlarmRepository alarmRepository;
    private final MemberRepository memberRepository;
    private final FCMService fcmService;
// AlarmService.java




    // Controller 의 호출없이 주기적으로 계속 실행
    @Scheduled(fixedRate = 10000)
    @Transactional
    public void sendEventNotification() {
        LocalDateTime now = LocalDateTime.now();
        int nowHour = now.getHour();
        int nowMinute = now.getMinute();

        //  1. 현재 시간에 해당하는 알림을 다 찾음
        //  2. 이 알림을 등록한 사용자들에게 전부 알림 전송 -> 비동기로
        List<Alarm> alarms = alarmRepository.findAll();

        for (Alarm alarm : alarms) {
            LocalDate alarmDate = alarm.getAlarmDateTime().toLocalDate();
            LocalTime alarmTime = alarm.getAlarmDateTime().toLocalTime();
            log.info("현재 시간: {}", now);
            log.info("알림 시간: {}", alarmTime);
            if (alarm.getAlarmDateTime().toLocalDate() == alarmDate && alarm.getAlarmDateTime().getHour() == nowHour &&
                    alarm.getAlarmDateTime().getMinute() == nowMinute) {
                AlarmDto alarmDto = AlarmDto.builder()
                        .title(alarm.getTitle())
                        .content(alarm.getContent())
                        .img(alarm.getImg())
                        .url(alarm.getUrl())
                        .email(alarm.getMember().getEmail())
                        .build();
                fcmService.sendByAlarm(alarmDto);
            }
        }
    }

    @Transactional
    public ResponseEntity<ApiResponseDto> createNotification(PostNotificationDto postNotificationDto, Principal principal) {
        String userEmail = principal.getName();
        System.out.println("ddddddddd" + userEmail);
        // 사용자 조회
        Member member = memberRepository.findByEmail(userEmail)
                .orElseThrow(() -> new CustomException(CustomErrorCode.USER_NOT_FOUND));

        Alarm alarm = Alarm.builder()
                .title(postNotificationDto.getTitle())
                .content(postNotificationDto.getContent())
                .img(postNotificationDto.getImg())
                .url(postNotificationDto.getUrl())
                .alarmDateTime(postNotificationDto.getAlarmDateTime())
                .member(member)
                .build();

        alarmRepository.save(alarm);

        return ResponseEntity.ok().body(ApiResponseDto.builder()
                .successStatus(HttpStatus.OK)
                .successContent(postNotificationDto.getAlarmDateTime() + "에 알림 전송을 합니다.")
                .build());
    }
}
