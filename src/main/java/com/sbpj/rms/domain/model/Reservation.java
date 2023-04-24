package com.sbpj.rms.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sbpj.rms.domain.form.AddReservationForm;
import lombok.*;
import org.apache.commons.lang3.RandomStringUtils;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue
    @Column(name = "reservation_id")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @JsonIgnore
    @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "shop_id")
    private Shop shop;

    private LocalDate date;
    private LocalTime time;
    private int pcnt;

    private LocalDateTime verifyExpiredAt;
    private String verificationCode;
    private boolean verify;

    private boolean approve;

    public static Reservation from(AddReservationForm form, Customer customer, Shop shop) {
        return Reservation.builder()
                .date(LocalDate.of(form.getYear(), form.getMonth(), form.getDate()))
                .time(LocalTime.of(form.getHour(), form.getMinute(), 0))
                .pcnt(form.getPcnt())
                .verificationCode(RandomStringUtils.random(10, true, true))
                .customer(customer)
                .shop(shop)
                .verifyExpiredAt(LocalDateTime
                        .of(form.getYear(), form.getMonth(), form.getDate(), form.getHour(), form.getMinute(), 0)
                        .minusMonths(10))
                .verify(false)
                .approve(false)
                .build();
    }

}
