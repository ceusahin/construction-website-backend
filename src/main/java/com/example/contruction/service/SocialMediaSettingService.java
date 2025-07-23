package com.example.contruction.service;

import com.example.contruction.entity.SocialMediaSetting;
import com.example.contruction.repository.SocialMediaSettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocialMediaSettingService {

    @Autowired
    private SocialMediaSettingRepository repository;

    public List<SocialMediaSetting> getAll() {
        return repository.findAll();
    }

    public SocialMediaSetting update(String platform, boolean visible, String url) {
        SocialMediaSetting setting = repository.findByPlatform(platform)
                .orElseThrow(() -> new RuntimeException("Platform not found"));

        setting.setVisible(visible);
        setting.setUrl(url);

        return repository.save(setting);
    }
}
