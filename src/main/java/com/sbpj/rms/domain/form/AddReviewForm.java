package com.sbpj.rms.domain.form;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddReviewForm {
    private Long shopId;
    private String comment;
    private int rating;
}
