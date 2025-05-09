package com.example.foss.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{
    private final CustomErrorCode customErrorCode;

    public CustomException(CustomErrorCode customErrorCode) {
        //super는 자바에서 부모 클래스의 생성자나 메서드를 호출할 때 사용하는 키워드
        super(customErrorCode.getMessage());
        this.customErrorCode = customErrorCode;
    }

    public CustomErrorCode getErrorCode() {
        return customErrorCode;
    }
}
