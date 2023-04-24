package com.sbpj.rms.controller;

import com.sbpj.rms.config.jwt.JwtAuthenticationProvider;
import com.sbpj.rms.config.jwt.UserVo;
import com.sbpj.rms.domain.dto.ReviewDto;
import com.sbpj.rms.domain.form.AddReservationForm;
import com.sbpj.rms.domain.form.AddReviewForm;
import com.sbpj.rms.domain.form.UpdateReviewForm;
import com.sbpj.rms.domain.model.*;
import com.sbpj.rms.exception.CustomException;
import com.sbpj.rms.service.ReservationService;
import com.sbpj.rms.service.ReviewService;
import com.sbpj.rms.service.ShopService;
import com.sbpj.rms.service.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.sbpj.rms.exception.ErrorCode.NOT_FOUND_SHOP;
import static com.sbpj.rms.exception.ErrorCode.NOT_FOUND_USER;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final ReservationService reservationService;
    private final ShopService shopService;
    private final ReviewService reviewService;
    private final JwtAuthenticationProvider provider;

    @GetMapping("/shops")
    public ResponseEntity<List<Shop>> getShopList() {
        return ResponseEntity.ok(shopService.findAll());
    }

    @GetMapping("/shopsByName")
    public ResponseEntity<List<Shop>> getShopListOrderByName() {
        return ResponseEntity.ok(shopService.findAllByOrderByNameAsc());
    }

    @GetMapping("/shopsByRating")
    public ResponseEntity<List<Shop>> getShopListOrderByRating() {
        return ResponseEntity.ok(shopService.findAllByOrderByRatingAsc());
    }

    @GetMapping("/shop/detail")
    public ResponseEntity<Optional<Shop>> getShopDetail(@RequestParam Long id) {
        return ResponseEntity.ok(shopService.findById(id));
    }

    @PostMapping("/customer/addReservation")
    public ResponseEntity<Reservation> addReservation(
            @RequestHeader(name = "X-AUTH-TOKEN") String token,
            @RequestBody AddReservationForm form) {
        UserVo vo = provider.getUserVo(token);
        Customer c = customerService.getCustomer(vo.getId(), vo.getEmail())
                .orElseThrow(() -> new CustomException(NOT_FOUND_USER));
        Shop s = shopService.findById(form.getShopId()).orElseThrow(
                () -> new CustomException(NOT_FOUND_SHOP));
        return ResponseEntity.ok(reservationService.addReservation(form, c, s));
    }

    /**
     * 리뷰 작성
     */
    @PostMapping("/customer/review")
    public ResponseEntity<ReviewDto> addReview(
            @RequestHeader(name = "X-AUTH-TOKEN") String token,
            @RequestBody AddReviewForm form) {
        UserVo vo = provider.getUserVo(token);
        Customer c = customerService.getCustomer(vo.getId(), vo.getEmail())
                .orElseThrow(() -> new CustomException(NOT_FOUND_USER));
        Shop s = shopService.findById(form.getShopId()).orElseThrow(
                () -> new CustomException(NOT_FOUND_SHOP));
        return ResponseEntity.ok(ReviewDto.from(
                reviewService.addReview(form, c, s)));
    }

    /**
     * 리뷰 수정
     */
    @PutMapping("/customer/review")
    public ResponseEntity<ReviewDto> updateReview(
            @RequestHeader(name = "X-AUTH-TOKEN") String token,
            @RequestBody UpdateReviewForm form) {
        UserVo vo = provider.getUserVo(token);
        Customer c = customerService.getCustomer(vo.getId(), vo.getEmail())
                .orElseThrow(() -> new CustomException(NOT_FOUND_USER));
        Shop s = shopService.findById(form.getShopId()).orElseThrow(
                () -> new CustomException(NOT_FOUND_SHOP));
        return ResponseEntity.ok(ReviewDto.from(
                reviewService.updateReview(form, c, s)));
    }

    /**
     * 리뷰 삭제
     */
    @DeleteMapping("/customer/review")
    public ResponseEntity<Void> deleteReview(
            @RequestHeader(name = "X-AUTH-TOKEN") String token,
            @RequestParam Long id) {
        reviewService.deleteReview(provider.getUserVo(token).getId(), id);
        return ResponseEntity.ok().build();
    }


}
