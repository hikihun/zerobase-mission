package com.sbpj.rms.domain.model;

import com.sbpj.rms.domain.form.AddShopForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_id")
    private Long id;
    private String name;
    private String address;
    private String information;
    private int tableCnt;
    private int rating;

    private LocalTime startTime;
    private LocalTime endTime;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "manager_id")
    private Manager manager;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
    private List<Reservation> reservations = new ArrayList<>();

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
    private List<Kiosk> kiosks = new ArrayList<>();

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    public static Shop from(AddShopForm form, Manager manager) {
        return Shop.builder()
                .name(form.getName())
                .address(form.getAddress())
                .information(form.getInformation())
                .tableCnt(form.getTableCnt())
                .rating(0)
                .startTime(LocalTime.of(form.getStartTime(), 0, 0))
                .endTime(LocalTime.of(form.getEndTime(), 0, 0))
                .manager(manager)
                .reservations(new ArrayList<>())
                .kiosks(new ArrayList<>())
                .build();
    }

}
