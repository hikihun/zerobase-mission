package com.sbpj.rms.config.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserVo {
    private Long id;
    private String email;
}