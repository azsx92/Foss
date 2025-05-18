package com.example.foss.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostNotificationDto {

    private String title;
    private String content;
    private String url;
    private String img;
    private LocalDateTime alarmDateTime;
}
