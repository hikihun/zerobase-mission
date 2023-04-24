package com.sbpj.rms.service;

import com.sbpj.rms.domain.form.AddShopForm;
import com.sbpj.rms.domain.model.Manager;
import com.sbpj.rms.domain.model.Shop;
import com.sbpj.rms.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ShopService {

    private final ShopRepository shopRepository;

    /**
     * 상점 등록
     */
    @Transactional
    public Shop addShop(AddShopForm form, Manager manager) {
        return shopRepository.save(Shop.from(form, manager));
    }

    /**
     * 상점 수정
     */
//    @Transactional
//    public Shop updateShop(ModifyShopForm form, Manager manager) {
//
//    }

    /**
     * 상점 삭제
     */
    @Transactional
    public void deleteShop() {

    }

    public List<Shop> findAll() {
        return shopRepository.findAll();
    }

    public List<Shop> findAllByOrderByNameAsc() {
        return shopRepository.findAllByOrderByNameAsc();
    }

    public List<Shop> findAllByOrderByRatingAsc() {
        return shopRepository.findAllByOrderByRatingDesc();
    }

    public List<Shop> findAllByOrderByDistanceAsc() {
        return shopRepository.findAllByOrderByRatingDesc();
    }

    public List<Shop> findShops(Long managerId) {
        return shopRepository.findByManagerId(managerId);
    }

    public Optional<Shop> findById(Long id) {
        return shopRepository.findById(id);
    }








}
