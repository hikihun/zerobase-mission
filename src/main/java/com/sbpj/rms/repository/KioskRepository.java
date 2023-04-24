package com.sbpj.rms.repository;

import com.sbpj.rms.domain.model.Kiosk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KioskRepository extends JpaRepository<Kiosk, Long> {

}
