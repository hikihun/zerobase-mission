package com.sbpj.rms.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manager_id")
    private Long id;
    private String name;
    private String email;
    private String password;
    private String phone;

    /**
     * 없으면
     * java.lang.IllegalStateException: Cannot call sendError() after the response has been committed 에러 발생
     */
    @JsonIgnore
    @OneToMany(mappedBy = "manager")
    private List<Shop> shops = new ArrayList<>();

    public static Manager from(SignUpForm form) {
        return Manager.builder()
                .name(form.getName())
                .email(form.getEmail().toLowerCase(Locale.ROOT))
                .password(form.getPassword())
                .phone(form.getPhone())
                .shops(new ArrayList<>())
                .build();
    }

}
