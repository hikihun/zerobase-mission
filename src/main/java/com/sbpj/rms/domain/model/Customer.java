package com.sbpj.rms.domain.model;

import com.sbpj.rms.domain.form.SignUpForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;
    private String name;
    private String email;
    private String password;
    private String phone;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Reservation> reservations = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    public static Customer from(SignUpForm form) {
        return Customer.builder()
                .name(form.getName())
                .email(form.getEmail().toLowerCase(Locale.ROOT))
                .password(form.getPassword())
                .phone(form.getPhone())
                .reservations(new ArrayList<>())
                .reviews(new ArrayList<>())
                .build();
    }
}
