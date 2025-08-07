package com.example.contruction.controller;

import com.example.contruction.dto.AboutSectionDTO;
import com.example.contruction.entity.AboutSection;
import com.example.contruction.service.AboutSectionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/about")
public class AboutSectionController {

    private final AboutSectionService service;

    public AboutSectionController(AboutSectionService service) {
        this.service = service;
    }

    // GET /api/construction/about/{languageCode}/{section}
    @GetMapping("/{languageCode}/{section}")
    public ResponseEntity<AboutSectionDTO> getByLanguageAndSection(
            @PathVariable String languageCode,
            @PathVariable String section
    ) {
        AboutSectionDTO dto = service.getByLanguageAndSection(languageCode, section);
        return ResponseEntity.ok(dto);
    }

    // PUT /api/construction/about/{languageCode}/{section}
    @PutMapping("/{languageCode}/{section}")
    public ResponseEntity<AboutSectionDTO> update(
            @PathVariable String languageCode,
            @PathVariable String section,
            @RequestBody AboutSectionDTO dto
    ) {
        AboutSectionDTO updated = service.updateOrCreate(languageCode, section, dto);
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/video")
    public ResponseEntity<?> updateVideoIdForAll(@RequestBody Map<String, String> request) {
        String videoId = request.get("youtubeVideoId");
        if (videoId == null || videoId.isEmpty()) {
            return ResponseEntity.badRequest().body("Video ID boş olamaz");
        }
        service.updateVideoIdForAll(videoId);
        return ResponseEntity.ok("Video ID tüm kayıtlara güncellendi.");
    }

    @GetMapping("/video")
    public ResponseEntity<Map<String, String>> getVideoId() {
        String videoId = service.getVideoId();
        if (videoId == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(Collections.singletonMap("videoId", videoId));
    }
}
