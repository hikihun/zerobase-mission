package com.sbpj.rms.service;

import com.sbpj.rms.domain.Manager;
import com.sbpj.rms.domain.ManagerForm;
import com.sbpj.rms.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ManagerService {

    private final ManagerRepository managerRepository;

    /**
     * 회원 가입
     */
    @Transactional
    public Manager join(Manager manager) {
        validateDuplicateManager(manager);
        managerRepository.save(manager);
        return manager;
    }

    private void validateDuplicateManager(Manager manager) {
        // exception
        Optional<Manager> result = managerRepository.findManagerByEmail(manager.getEmail());
        if (!result.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

//    /**
//     * 상점 등록
//     */
//    @Transactional
//    public Long registrationShop(Shop)

}
