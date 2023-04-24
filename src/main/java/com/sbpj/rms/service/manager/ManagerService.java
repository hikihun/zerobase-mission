package com.sbpj.rms.service.manager;

import com.sbpj.rms.domain.form.SignUpForm;
import com.sbpj.rms.domain.form.UpdateReservationForm;
import com.sbpj.rms.domain.model.Manager;
import com.sbpj.rms.domain.form.SignInForm;
import com.sbpj.rms.domain.model.Reservation;
import com.sbpj.rms.exception.CustomException;
import com.sbpj.rms.repository.ManagerRepository;
import com.sbpj.rms.repository.ReservationRepository;
import com.sbpj.rms.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.sbpj.rms.exception.ErrorCode.ALREADY_REGISTER_USER;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ManagerService {

    private final ManagerRepository managerRepository;
    private final ShopRepository shopRepository;
    private final ReservationRepository reservationRepository;

    /**
     * 회원 가입
     */
    @Transactional
    public Manager signup(SignUpForm form) {
        validateDuplicateManager(form);
        return managerRepository.save(Manager.from(form));
    }

    /**
     *  중복 회원 예외 처리
     */
    private void validateDuplicateManager(SignUpForm form) {
        // exception
        Optional<Manager> result = managerRepository.findByEmail(form.getEmail());
        if (!result.isEmpty()) {
            throw new CustomException(ALREADY_REGISTER_USER);
        }
    }

    /**
     * 로그인
     */
    @Transactional
    public Optional<Manager> findValidManager(SignInForm form) {
        return managerRepository.findByEmail(form.getEmail()).stream()
                .filter(manager -> manager.getPassword().equals(form.getPassword()))
                .findFirst();
    }

    public Optional<Manager> getManager(Long managerId, String email) {
        return managerRepository.findById(managerId).stream()
                .filter(manager -> manager.getEmail().equals(email))
                .findFirst();
    }

    /**
     * 예약 정보 확인
     */
    public List<Reservation> getReservations(Long shopId, LocalDate date) {
        return reservationRepository.findAllByShop_IdAndDate(shopId, date);
    }

    /**
     * 예약 정보 승인
     */
    @Transactional
    public Reservation approveReservation(Long managerId, UpdateReservationForm form) {
        Reservation reservation = reservationRepository.findById(form.getReservationId()).get();
        reservation.setApprove(true);
        return reservation;
    }

    /**
     * 예약 정보 거부
     */

}
