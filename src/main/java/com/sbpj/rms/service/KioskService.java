package com.sbpj.rms.service;

import com.sbpj.rms.domain.form.VerifyReservationForm;
import com.sbpj.rms.domain.model.Kiosk;
import com.sbpj.rms.domain.model.Reservation;
import com.sbpj.rms.domain.model.Shop;
import com.sbpj.rms.exception.CustomException;
import com.sbpj.rms.repository.KioskRepository;
import com.sbpj.rms.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.sbpj.rms.exception.ErrorCode.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class KioskService {

    private final ReservationRepository reservationRepository;
    private final KioskRepository kioskRepository;

    @Transactional
    public Kiosk addKiosk(Shop shop) {
        return kioskRepository.save(Kiosk.from(shop));
    }

    public Optional<Kiosk> findById(Long kioskId) {
        return kioskRepository.findById(kioskId);
    }

    public Reservation validateReservation(Long shopId, VerifyReservationForm form) {
        Reservation reservation = reservationRepository.findByShop_IdAndVerificationCode(shopId, form.getVerificationCode())
                .orElseThrow(() -> new CustomException(NOT_FOUND_RESERVATION));
        if (LocalDateTime.now().isBefore(reservation.getVerifyExpiredAt())) {
            throw new CustomException(EXPIRE_TIME);
        }
        reservation.setVerify(true);
        return reservation;
    }


}
