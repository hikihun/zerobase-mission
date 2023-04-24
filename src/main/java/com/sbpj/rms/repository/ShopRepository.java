package com.sbpj.rms.repository;

import com.sbpj.rms.domain.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
    List<Shop> findByManagerId(Long managerId);
    List<Shop> findAllByOrderByNameAsc();
    List<Shop> findAllByOrderByRatingDesc();
}
