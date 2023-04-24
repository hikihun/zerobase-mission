package com.sbpj.rms.controller;

import com.sbpj.rms.config.jwt.JwtAuthenticationProvider;
import com.sbpj.rms.config.jwt.UserVo;
import com.sbpj.rms.domain.form.UpdateReservationForm;
import com.sbpj.rms.domain.dto.ReservationDto;
import com.sbpj.rms.domain.form.AddShopForm;
import com.sbpj.rms.domain.form.ManagerReservationForm;
import com.sbpj.rms.domain.model.Manager;
import com.sbpj.rms.domain.model.Reservation;
import com.sbpj.rms.domain.model.Shop;
import com.sbpj.rms.exception.CustomException;
import com.sbpj.rms.service.ReservationService;
import com.sbpj.rms.service.ShopService;
import com.sbpj.rms.service.manager.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static com.sbpj.rms.exception.ErrorCode.NOT_FOUND_USER;

@RestController
@RequestMapping("/manager")
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerService managerService;
    private final ShopService shopService;
    private final ReservationService reservationService;
    private final JwtAuthenticationProvider provider;

    /**
     * 상점 추가
     */
    @PostMapping("/addShop")
    public ResponseEntity<Shop> addShop(
            @RequestHeader(name = "X-AUTH-TOKEN") String token,
            @RequestBody AddShopForm form) {
        UserVo vo = provider.getUserVo(token);
        Manager m = managerService.getManager(vo.getId(), vo.getEmail())
                .orElseThrow(() -> new CustomException(NOT_FOUND_USER));
        return ResponseEntity.ok(shopService.addShop(form, m));
    }

    /**
     * 상점 보기
     */


    /**
     * 상점 수정
     */
//    @PutMapping("/updateShop")
//    public ResponseEntity<ShopDto> updateShop(
//            @RequestHeader(name = "X-AUTH-TOKEN") String token,
//            @RequestBody UpdateShopForm form)
//    )

    /**
     * 상점 삭제
     */



    /**
     * 예약 정보 확인
     */
    @PostMapping("/reservation")
    public List<Reservation> showReservations(
            @RequestHeader(name = "X-AUTH-TOKEN") String token,
            @RequestBody ManagerReservationForm form) {

//        List<Shop> shops = shopService.findShops(provider.getUserVo(token).getId());
        return managerService.getReservations(form.getShopId(), form.getDate());
    }

    /**
     * 예약 승인
     */
    @PutMapping("/reservation")
    public ResponseEntity<Reservation> approveReservation(
            @RequestHeader(name = "X-AUTH-TOKEN") String token,
            @RequestBody UpdateReservationForm form) {
        return ResponseEntity.ok(
                managerService.approveReservation(provider.getUserVo(token).getId(), form));
    }

    /**
     * 예약 거부
     */


}