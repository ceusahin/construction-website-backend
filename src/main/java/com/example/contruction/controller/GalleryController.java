package com.example.contruction.controller;

import com.example.contruction.entity.GalleryImage;
import com.example.contruction.repository.GalleryRepository;
import com.example.contruction.service.ImageUploadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/gallery")
public class GalleryController {

    private final ImageUploadService uploadService;
    private final GalleryRepository galleryRepository;

    public GalleryController(ImageUploadService uploadService, GalleryRepository galleryRepository) {
        this.uploadService = uploadService;
        this.galleryRepository = galleryRepository;
    }

    @PostMapping
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file,
                                         @RequestParam("title") String title) {
        try {
            Map uploadResult = uploadService.uploadImage(file, "construction-site/context");
            String imageUrl = (String) uploadResult.get("secure_url");
            String publicId = (String) uploadResult.get("public_id");

            GalleryImage image = new GalleryImage(null, title, imageUrl, publicId);
            galleryRepository.save(image);

            return ResponseEntity.ok(image);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Upload failed: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllImages() {
        return ResponseEntity.ok(galleryRepository.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteImage(@PathVariable Long id) {
        Optional<GalleryImage> imageOpt = galleryRepository.findById(id);

        if (imageOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        GalleryImage image = imageOpt.get();
        try {
            uploadService.deleteImage(image.getPublicId());
            galleryRepository.deleteById(id);
            return ResponseEntity.ok("Image deleted.");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Delete failed: " + e.getMessage());
        }
    }
}
