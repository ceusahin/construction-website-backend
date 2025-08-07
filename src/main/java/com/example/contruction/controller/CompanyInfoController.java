package com.example.contruction.controller;

import com.example.contruction.entity.CompanyInfo;
import com.example.contruction.service.CompanyInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
public class CompanyInfoController {

    private final CompanyInfoService service;

    public CompanyInfoController(CompanyInfoService service) {
        this.service = service;
    }

    @GetMapping("/{language}")
    public ResponseEntity<CompanyInfo> getCompanyInfoByLanguage(@PathVariable String language) {
        try {
            CompanyInfo info = service.getCompanyInfoByLanguage(language);
            return ResponseEntity.ok(info);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{language}")
    public ResponseEntity<CompanyInfo> updateCompanyInfoByLanguage(
            @PathVariable String language,
            @RequestBody CompanyInfo newInfo
    ) {
        try {
            CompanyInfo updated = service.updateCompanyInfoByLanguage(language, newInfo);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{language}")
    public ResponseEntity<CompanyInfo> createOrUpdate(
            @PathVariable String language,
            @RequestBody CompanyInfo newInfo
    ) {
        CompanyInfo saved = service.createOrUpdateByLanguage(language, newInfo);
        return ResponseEntity.ok(saved);
    }
}
