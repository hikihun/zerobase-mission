package com.sbpj.rms.domain.dto;

import com.sbpj.rms.domain.model.Reservation;
import com.sbpj.rms.domain.model.Review;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReservationDto {
    private Long id;
    private Long shopId;
    private LocalDate date;
    private LocalTime time;
    private int pcnt;
    private LocalDateTime verifyExpiredAt;
    private String verificationCode;
    private boolean verify;
    private boolean approve;

    /**
     * Reservation Entity을 받아서 ReservationDto 생성
     */
    public static ReservationDto from(Reservation reservation) {
        return ReservationDto.builder()
                .id(reservation.getId())
//                .shopId(reservation.getShop().getId())
                .date(reservation.getDate())
                .time(reservation.getTime())
                .pcnt(reservation.getPcnt())
                .verifyExpiredAt(reservation.getVerifyExpiredAt())
                .verificationCode(reservation.getVerificationCode())
                .verify(reservation.isVerify())
                .approve(reservation.isApprove())
                .build();
    }
}
