package com.sbpj.rms.application;

import com.sbpj.rms.config.jwt.JwtAuthenticationProvider;
import com.sbpj.rms.config.jwt.UserType;
import com.sbpj.rms.domain.form.SignInForm;
import com.sbpj.rms.domain.model.Customer;
import com.sbpj.rms.domain.model.Manager;
import com.sbpj.rms.exception.CustomException;
import com.sbpj.rms.service.CustomerService;
import com.sbpj.rms.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.sbpj.rms.exception.ErrorCode.LOGIN_CHECK_FAIL;

@Service
@RequiredArgsConstructor
public class SignInApplication {

    private final ManagerService managerService;
    private final CustomerService customerService;
    private final JwtAuthenticationProvider provider;

    /**
     * 매니저 로그인 로직
     * 로그인 성공시 jwt토큰 리턴
     */
    public String managerLoginToken(SignInForm form) {
        //  1. 로그인 가능 여부
        Manager m = managerService.findValidManager(form)
                .orElseThrow(() -> new CustomException(LOGIN_CHECK_FAIL));
        //  2. 토큰 발행
        //  3. 토큰 response
        return provider.createToken(m.getEmail(), m.getId(), UserType.MANAGER);
    }

    /**
     * 고객 로그인 로직
     * 로그인 성공시 jwt토큰 리턴
     */
    public String customerLoginToken(SignInForm form) {
        Customer c = customerService.findValidCustomer(form)
                .orElseThrow(() -> new CustomException(LOGIN_CHECK_FAIL));

        return provider.createToken(c.getEmail(), c.getId(), UserType.CUSTOMER);
    }

}
