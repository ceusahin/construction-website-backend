package com.example.contruction.controller;

import com.example.contruction.entity.Logo;
import com.example.contruction.service.LogoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/logo")
public class LogoController {

    private final LogoService logoService;

    public LogoController(LogoService logoService) {
        this.logoService = logoService;
    }

    @GetMapping
    public ResponseEntity<Logo> getLogo() {
        Logo logo = logoService.getLogo();
        return logo != null ? ResponseEntity.ok(logo) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> uploadLogo(@RequestParam("file") MultipartFile file) {
        try {
            return ResponseEntity.ok(logoService.saveOrUpdateLogo(file));
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Upload failed: " + e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteLogo() {
        try {
            logoService.deleteLogo();
            return ResponseEntity.ok("Logo silindi.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Logo silinirken hata oluştu: " + e.getMessage());
        }
    }
}
