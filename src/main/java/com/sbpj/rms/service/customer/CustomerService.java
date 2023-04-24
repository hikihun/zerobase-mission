package com.sbpj.rms.service.customer;

import com.sbpj.rms.domain.form.SignInForm;
import com.sbpj.rms.domain.form.SignUpForm;
import com.sbpj.rms.domain.model.Customer;
import com.sbpj.rms.exception.CustomException;
import com.sbpj.rms.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.sbpj.rms.exception.ErrorCode.ALREADY_REGISTER_USER;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    /**
     * 회원 가입
     */
    @Transactional
    public Customer signup(SignUpForm form) {
        validateDuplicateCustomer(form);
        return customerRepository.save(Customer.from(form));
    }

    /**
     *  중복 회원 예외 처리
     */
    private void validateDuplicateCustomer(SignUpForm form) {
        // exception
        Optional<Customer> result = customerRepository.findByEmail(form.getEmail());
        if (!result.isEmpty()) {
            throw new CustomException(ALREADY_REGISTER_USER);
        }
    }

    /**
     * 로그인
     */
    @Transactional
    public Optional<Customer> findValidCustomer(SignInForm form) {
        return customerRepository.findByEmail(form.getEmail()).stream()
                .filter(manager -> manager.getPassword().equals(form.getPassword()))
                .findFirst();
    }

    public Optional<Customer> getCustomer(Long managerId, String email) {
        return customerRepository.findById(managerId).stream()
                .filter(manager -> manager.getEmail().equals(email))
                .findFirst();
    }

}
