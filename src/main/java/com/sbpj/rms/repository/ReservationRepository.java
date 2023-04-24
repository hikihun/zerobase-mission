package com.sbpj.rms.repository;

import com.sbpj.rms.domain.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByShop_Id(Long shopId);
    List<Reservation> findAllByShop_IdAndDate(Long shopId, LocalDate date);
    Optional<Reservation> findByShop_IdAndVerificationCode(Long shopId, String verificationCode);
}
