package com.sbpj.rms.controller;

import com.sbpj.rms.domain.form.VerifyReservationForm;
import com.sbpj.rms.domain.model.Kiosk;
import com.sbpj.rms.domain.model.Reservation;
import com.sbpj.rms.domain.model.Shop;
import com.sbpj.rms.exception.CustomException;
import com.sbpj.rms.repository.ReservationRepository;
import com.sbpj.rms.service.KioskService;
import com.sbpj.rms.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.sbpj.rms.exception.ErrorCode.NOT_FOUND_SHOP;

@RestController
@RequiredArgsConstructor
public class KioskController {

    private final ReservationRepository reservationRepository;
    private final KioskService kioskService;
    private final ShopService shopService;

    @PostMapping("/addKiosk")
    public ResponseEntity<Kiosk> addKiosk(@RequestParam Long shopId) {
        Shop shop = shopService.findById(shopId).orElseThrow(
                () -> new CustomException(NOT_FOUND_SHOP));
        return ResponseEntity.ok(kioskService.addKiosk(shop));
    }

    @PostMapping("/verify")
    public ResponseEntity<Reservation> verify(@RequestBody VerifyReservationForm form) {
        Long shopId = kioskService.findById(form.getShopId()).get().getShop().getId();
        return ResponseEntity.ok(kioskService.validateReservation(shopId, form));
    }

}
