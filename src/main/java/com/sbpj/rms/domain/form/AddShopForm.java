package com.sbpj.rms.domain.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddShopForm {
    private String name;
    private String address;
    private String information;
    private int tableCnt;
    private int startTime;
    private int endTime;
}
