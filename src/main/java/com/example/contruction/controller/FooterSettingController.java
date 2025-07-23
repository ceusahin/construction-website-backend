package com.example.contruction.controller;

import com.example.contruction.entity.FooterSetting;
import com.example.contruction.service.FooterSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/footer-settings")
@CrossOrigin(origins = "http://localhost:5173")
public class FooterSettingController {

    @Autowired
    private FooterSettingService service;

    @GetMapping
    public List<FooterSetting> getAllSettings() {
        return service.getAll();
    }

    @PutMapping("/{sectionKey}")
    public FooterSetting updateVisibility(
            @PathVariable String sectionKey,
            @RequestParam boolean visible
    ) {
        return service.updateVisibility(sectionKey, visible);
    }
}