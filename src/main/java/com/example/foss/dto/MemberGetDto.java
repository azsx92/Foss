package com.example.foss.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class MemberGetDto {
        private String name;
        private String email;
        private String major;
        private String phone;
}
