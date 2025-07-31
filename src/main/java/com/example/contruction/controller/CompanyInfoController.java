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

    @GetMapping("/{id}")
    public ResponseEntity<CompanyInfo> getCompanyInfo(@PathVariable Long id) {
        return service.getCompanyInfo(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyInfo> updateCompanyInfo(@PathVariable Long id, @RequestBody CompanyInfo newInfo) {
        try {
            CompanyInfo updated = service.updateCompanyInfo(id, newInfo.getCompanyName(), newInfo.getCompanyDescription());
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}