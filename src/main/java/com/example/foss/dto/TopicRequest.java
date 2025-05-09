package com.example.foss.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class TopicRequest {

    private String topic;

    @JsonCreator
    public TopicRequest(String topic) {
        this.topic = topic;
    }

}
