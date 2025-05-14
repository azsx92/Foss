package com.example.foss.service;

import com.example.foss.auth.JwtUtil;
import com.example.foss.domain.Member;
import com.example.foss.dto.*;
import com.example.foss.exception.CustomErrorCode;
import com.example.foss.exception.CustomException;
import com.example.foss.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {
    private final MemberRepository memberRepository ;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public ResponseEntity<ApiResponseDto> register(RegisterRequest request) {
        Optional<Member> member = memberRepository.findByEmail(request.getEmail());
        if (member.isPresent()) {
            throw new CustomException(CustomErrorCode.DUPLICATED_EMAIL);
        }

        String password = bCryptPasswordEncoder.encode(request.getPassword());

        Member newMember = Member.builder()
                .email(request.getEmail())
                .password(password)
                .build();
        memberRepository.save(newMember);

        return ResponseEntity.ok().body(ApiResponseDto.builder()
                .successStatus(HttpStatus.OK)
                .successContent("화원가입 완료")
                .data(request.getEmail())
                .build()
        );
    }

    @Transactional
    public ResponseEntity<LoginResponse> login(MemberDto.LoginRequest loginRequest) {

        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(CustomErrorCode.LOGIN_FAILED));

        if (!encoder.matches(password, member.getPassword())) {
            throw new CustomException(CustomErrorCode.LOGIN_FAILED);
        }

        MemberDto.MemberInfoDto memberInfoDto = MemberDto.MemberInfoDto.builder()
                .memberId(member.getId())
                .email(member.getEmail())
                .password(member.getPassword())
                .build();

        String accessToken = jwtUtil.createAccessToken(memberInfoDto);
        String refreshToken = jwtUtil.createRefreshToken(memberInfoDto);

        LoginResponse loginResponse = LoginResponse.builder()
                .id(member.getId())
                .grantType("Authorization")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .email(member.getEmail())
                .build();

        return ResponseEntity.ok().body(loginResponse);
    }

    public LoginResponse reissueAccessToken(ReissueTokenDto refreshToken) {
        String token = refreshToken.getRefreshToken();
        if (!jwtUtil.validateToken(token)) {
            throw new CustomException(CustomErrorCode.UNAUTHORIZED);
        }

        Long memberId = jwtUtil.getUserId(token);

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(CustomErrorCode.USER_NOT_FOUND));

        MemberDto.MemberInfoDto memberInfoDto = MemberDto.MemberInfoDto.builder()
                .memberId(member.getId())
                .email(member.getEmail())
                .password(member.getPassword())
                .build();

        String accessToken = jwtUtil.createAccessToken(memberInfoDto);
        return LoginResponse.builder()
                .id(member.getId())
                .grantType("Authorization")
                .accessToken(accessToken)
                .refreshToken(token)
                .build();
    }

    public boolean duplicateEmail(String email) {
        return !memberRepository.existsByEmail(email);
    }
}
