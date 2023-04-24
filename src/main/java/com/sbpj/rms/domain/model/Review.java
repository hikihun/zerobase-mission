package com.sbpj.rms.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sbpj.rms.domain.form.AddReviewForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;
    private String comment;
    private int rating;

    @JsonIgnore
    @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @JsonIgnore
    @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "shop_id")
    private Shop shop;

    public static Review from(AddReviewForm form, Customer customer, Shop shop) {
        return Review.builder()
                .comment(form.getComment())
                .rating(form.getRating())
                .customer(customer)
                .shop(shop)
                .build();
    }

}
