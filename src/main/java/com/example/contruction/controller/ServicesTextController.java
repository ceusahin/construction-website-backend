package com.example.contruction.controller;

import com.example.contruction.dto.ServicesTextDTO;
import com.example.contruction.service.ServicesTextService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/services-text")
public class ServicesTextController {

    private final ServicesTextService service;

    public ServicesTextController(ServicesTextService service) {
        this.service = service;
    }

    // GET /api/construction/services-text/{languageCode}
    @GetMapping("/{languageCode}")
    public ResponseEntity<ServicesTextDTO> getByLanguage(@PathVariable String languageCode) {
        ServicesTextDTO dto = service.getByLanguage(languageCode);
        return ResponseEntity.ok(dto);
    }

    // PUT /api/construction/services-text/{languageCode}
    @PutMapping("/{languageCode}")
    public ResponseEntity<ServicesTextDTO> update(
            @PathVariable String languageCode,
            @RequestBody ServicesTextDTO dto
    ) {
        ServicesTextDTO updated = service.updateOrCreate(languageCode, dto);
        return ResponseEntity.ok(updated);
    }
}
