package com.example.contruction.controller;

import com.example.contruction.entity.SeoSettings;
import com.example.contruction.service.SeoSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seo")
public class SeoSettingsController {

    @Autowired
    private SeoSettingsService service;

    @GetMapping
    public SeoSettings getSeo() {
        return service.getSeo();
    }

    @PutMapping
    public SeoSettings updateSeo(@RequestBody SeoSettings seo) {
        return service.updateSeo(seo);
    }
}