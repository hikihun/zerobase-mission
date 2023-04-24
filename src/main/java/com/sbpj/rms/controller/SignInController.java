package com.sbpj.rms.controller;

import com.sbpj.rms.application.SignInApplication;
import com.sbpj.rms.domain.form.SignInForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("siginin")
@RequiredArgsConstructor
public class SignInController {

    private final SignInApplication signInApplication;

    /**
     * 매니저 로그인
     */
    @PostMapping("/manager")
    public ResponseEntity<String> signInManager(@RequestBody SignInForm form) {
        return ResponseEntity.ok(signInApplication.managerLoginToken(form));
    }

    /**
     * 고객 로그인
     */
    @PostMapping("/customer")
    public ResponseEntity<String> signInCustomer(@RequestBody SignInForm form) {
        return ResponseEntity.ok(signInApplication.customerLoginToken(form));
    }
}
