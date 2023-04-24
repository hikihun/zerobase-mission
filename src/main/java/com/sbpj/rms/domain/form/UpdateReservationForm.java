package com.sbpj.rms.domain.form;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateReservationForm {
    private Long reservationId;
}
