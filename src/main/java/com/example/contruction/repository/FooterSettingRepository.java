package com.example.contruction.repository;

import com.example.contruction.entity.FooterSetting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FooterSettingRepository extends JpaRepository<FooterSetting, Long> {
    Optional<FooterSetting> findBySectionKey(String sectionKey);
}
