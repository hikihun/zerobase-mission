package com.sbpj.rms.application;

import com.sbpj.rms.domain.form.SignUpForm;
import com.sbpj.rms.domain.model.Customer;
import com.sbpj.rms.domain.model.Manager;
import com.sbpj.rms.service.customer.CustomerService;
import com.sbpj.rms.service.manager.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpApplication {

    private final ManagerService managerService;
    private final CustomerService customerService;

    public String managerSignUp(SignUpForm form) {
        Manager m = managerService.signup(form);
        return "회원 가입에 성공하셨습니다.";
    }

    public String customerSignUp(SignUpForm form) {
        Customer c = customerService.signup(form);
        return "회원 가입에 성공하셨습니다.";
    }

}
