package com.sbpj.rms.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    // 회원가입 에러
    ALREADY_REGISTER_USER(HttpStatus.BAD_REQUEST, "이미 가입된 회원입니다."),
    // 로그인 에러
    NOT_FOUND_USER(HttpStatus.BAD_REQUEST, "일치하는 회원이 없습니다."),
    LOGIN_CHECK_FAIL(HttpStatus.BAD_REQUEST, "아이디 혹은 패스워드를 확인해 주세요"),


    NOT_FOUND_SHOP(HttpStatus.BAD_REQUEST, "등록되지 않은 가게입니다."),

    NOT_FOUND_RESERVATION(HttpStatus.BAD_REQUEST, "예약정보를 찾을 수 없습니다."),
    EXPIRE_TIME(HttpStatus.BAD_REQUEST, "예약시간이 지났습니다."),


    NOT_FOUND_REVIEW(HttpStatus.BAD_REQUEST, "리뷰를 찾을 수 없습니다.");




    private final HttpStatus httpStatus;
    private final String detail;
}