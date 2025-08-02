package com.example.contruction.controller;

import com.example.contruction.entity.SocialMediaSetting;
import com.example.contruction.service.SocialMediaSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/social-media")
public class SocialMediaSettingController {

    @Autowired
    private SocialMediaSettingService service;

    @GetMapping
    public List<SocialMediaSetting> getAll() {
        return service.getAll();
    }

    @PutMapping("/{platform}")
    public SocialMediaSetting updateSetting(
            @PathVariable String platform,
            @RequestParam boolean visible,
            @RequestParam String url
    ) {
        return service.update(platform, visible, url);
    }
}