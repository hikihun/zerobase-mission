package com.sbpj.rms.service;

import com.sbpj.rms.domain.form.AddReservationForm;
import com.sbpj.rms.domain.model.Customer;
import com.sbpj.rms.domain.model.Reservation;
import com.sbpj.rms.domain.model.Shop;
import com.sbpj.rms.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    @Transactional
    public Reservation addReservation(AddReservationForm form, Customer customer, Shop shop) {
        return reservationRepository.save(Reservation.from(form, customer, shop));
    }

    @Transactional
    public Reservation updateReservation() {
        return null;
    }

    @Transactional
    public void deleteReservation() {

    }



}
