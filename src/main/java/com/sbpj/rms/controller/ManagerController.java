package com.sbpj.rms.controller;

import com.sbpj.rms.domain.Manager;
import com.sbpj.rms.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerService managerService;

    /**
     *  매니저 회원 가입
     */
    @PostMapping("/join/manager")
    public Manager join(@RequestBody Manager manager) {
        return managerService.join(Manager.builder()
                .name(manager.getName())
                .email(manager.getEmail())
                .password(manager.getPassword())
                .phone(manager.getPhone())
                .shops(manager.getShops())
                .build());
    }
}
