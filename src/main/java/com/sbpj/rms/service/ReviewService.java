package com.sbpj.rms.service;

import com.sbpj.rms.domain.form.AddReviewForm;
import com.sbpj.rms.domain.form.UpdateReviewForm;
import com.sbpj.rms.domain.model.Customer;
import com.sbpj.rms.domain.model.Review;
import com.sbpj.rms.domain.model.Shop;
import com.sbpj.rms.exception.CustomException;
import com.sbpj.rms.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.sbpj.rms.exception.ErrorCode.NOT_FOUND_REVIEW;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Transactional
    public Review addReview(AddReviewForm form, Customer customer, Shop shop) {
        return reviewRepository.save(Review.from(form, customer, shop));
    }

    @Transactional
    public Review updateReview(UpdateReviewForm form, Customer c, Shop s) {
        Review review = reviewRepository.findByCustomer_IdAndId(c.getId(), form.getId())
                .orElseThrow(() -> new CustomException(NOT_FOUND_REVIEW))
                .toBuilder()
                .comment(form.getComment())
                .rating(form.getRating())
                .customer(c)
                .shop(s)
                .build();
        return reviewRepository.save(review);
    }

    @Transactional
    public void deleteReview(Long customerId, Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new CustomException(NOT_FOUND_REVIEW));
        reviewRepository.delete(review);
    }
}
