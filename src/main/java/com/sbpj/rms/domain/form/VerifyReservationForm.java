package com.sbpj.rms.domain.form;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class VerifyReservationForm {
    private Long shopId;
    private String verificationCode;
}
