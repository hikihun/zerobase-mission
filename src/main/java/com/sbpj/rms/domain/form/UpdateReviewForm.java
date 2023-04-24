package com.sbpj.rms.domain.form;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateReviewForm {
    private Long id;
    private Long shopId;
    private String comment;
    private int rating;
}
