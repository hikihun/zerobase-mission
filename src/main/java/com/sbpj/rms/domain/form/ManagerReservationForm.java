package com.sbpj.rms.domain.form;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class ManagerReservationForm {
    private Long shopId;
    private LocalDate date;
}
