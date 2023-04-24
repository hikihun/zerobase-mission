package com.sbpj.rms.domain.dto;

import com.sbpj.rms.domain.model.Review;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReviewDto {
    private Long id;
    private Long customerId;
    private Long shopId;
    private String comment;
    private int rating;

    /**
     * Review Entity을 받아서 ReviewDto 생성
     */
    public static ReviewDto from(Review review) {
        return ReviewDto.builder()
                .id(review.getId())
                .comment(review.getComment())
                .rating(review.getRating())
                .customerId(review.getCustomer().getId())
                .shopId(review.getShop().getId())
                .build();
    }
}
