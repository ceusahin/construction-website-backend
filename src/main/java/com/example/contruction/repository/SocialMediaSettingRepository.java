package com.example.contruction.repository;

import com.example.contruction.entity.SocialMediaSetting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SocialMediaSettingRepository extends JpaRepository<SocialMediaSetting, Long> {
    Optional<SocialMediaSetting> findByPlatform(String platform);
}
