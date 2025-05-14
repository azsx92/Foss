package com.example.foss.controller;

import com.example.foss.dto.*;
import com.example.foss.service.FCMService;
import com.example.foss.service.MemberService;
import com.example.foss.service.TokenTopicService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class MemberController {
    private final MemberService memberService;
    private final TokenTopicService tokenTopicService;



    @PostMapping("/register")
    public ResponseEntity<ApiResponseDto> register(@RequestBody RegisterRequest request)  throws IOException {
        return memberService.register(request);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody MemberDto.LoginRequest loginRequest)  {
        tokenTopicService.saveFCMToken(loginRequest);
        return memberService.login(loginRequest);
    }

    @PatchMapping("/reissue-token")
    public ResponseEntity<LoginResponse> reissueAccessToken(@RequestBody ReissueTokenDto refreshToken)  {
        LoginResponse token = memberService.reissueAccessToken(refreshToken);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }
    @GetMapping("/duplicateEmail")
    public ResponseEntity<Boolean> duplicateEmail(@RequestParam(name = "email") String email)  {
        Boolean res = memberService.duplicateEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}
