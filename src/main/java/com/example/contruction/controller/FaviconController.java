package com.example.contruction.controller;

import com.example.contruction.entity.Favicon;
import com.example.contruction.service.FaviconService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/favicon")
public class FaviconController {

    private final FaviconService faviconService;

    public FaviconController(FaviconService faviconService) {
        this.faviconService = faviconService;
    }

    @GetMapping
    public ResponseEntity<Favicon> getFavicon() {
        Favicon fav = faviconService.getFavicon();
        return fav != null ? ResponseEntity.ok(fav) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> uploadFavicon(@RequestParam("file") MultipartFile file) {
        try {
            return ResponseEntity.ok(faviconService.saveOrUpdateFavicon(file));
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Upload failed: " + e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteFavicon() {
        try {
            faviconService.deleteFavicon();
            return ResponseEntity.ok("Favicon silindi.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Favicon silinirken hata oluştu: " + e.getMessage());
        }
    }

}
