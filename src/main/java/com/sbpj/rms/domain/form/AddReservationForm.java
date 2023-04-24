package com.sbpj.rms.domain.form;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddReservationForm {
    private Long shopId;
    private int year;
    private int month;
    private int date;
    private int hour;
    private int minute;
    private int pcnt;
}
