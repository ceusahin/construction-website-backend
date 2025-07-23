package com.example.contruction.service;

import com.example.contruction.entity.FooterSetting;
import com.example.contruction.repository.FooterSettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FooterSettingService {

    @Autowired
    private FooterSettingRepository repository;

    public List<FooterSetting> getAll() {
        return repository.findAll();
    }

    public FooterSetting updateVisibility(String sectionKey, boolean visible) {
        FooterSetting setting = repository.findBySectionKey(sectionKey)
                .orElseThrow(() -> new RuntimeException("Section not found"));
        setting.setVisible(visible);
        return repository.save(setting);
    }
}
