package com.example.foss.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlarmDto {

    private String email;
    private LocalDateTime alarmDateTime;
    private String title;
    private String content;
    private String img;
    private String url;
}
