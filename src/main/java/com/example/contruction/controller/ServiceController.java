package com.example.contruction.controller;

import com.example.contruction.dto.ServiceDTO;
import com.example.contruction.dto.ServiceTranslationDTO;
import com.example.contruction.service.ServiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/services")
public class ServiceController {

    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    // 🌐 Site: Dil seçimine göre servis çevirilerini getir (id bilgisi yok, sadece çeviri)
    @GetMapping
    public List<ServiceDTO> getServicesByLanguageWithImages(@RequestParam(name = "lang", defaultValue = "tr") String lang) {
        return serviceService.getServicesByLanguageWithImages(lang);
    }

    // 🌐 Site: Tek servis ve tek dil için çeviri getir
    @GetMapping("/{id}")
    public ServiceTranslationDTO getServiceTranslation(
            @PathVariable Long id,
            @RequestParam(name = "lang", defaultValue = "tr") String lang) {
        return serviceService.getTranslationByServiceIdAndLanguage(id, lang);
    }

    // 🔒 Admin: Tüm servisleri tüm diller ve resim bilgileri ile getir
    @GetMapping("/admin")
    public List<ServiceDTO> getAllServices() {
        return serviceService.getAllServicesForAdmin();
    }

    // 🔒 Admin: Yeni servis ekle (image alanları da dahil)
    @PostMapping("/admin/add")
    public ServiceDTO createService(@RequestBody ServiceDTO dto) {
        return serviceService.addService(dto);
    }

    // 🔒 Admin: Servis sil
    @DeleteMapping("/admin/{id}")
    public ResponseEntity<?> deleteService(@PathVariable Long id) {
        serviceService.deleteService(id);
        return ResponseEntity.ok().build();
    }

    // 🔒 Admin: Servisi (çeviri + resim) tek seferde güncelle
    @PutMapping("/admin/{id}")
    public ResponseEntity<ServiceDTO> updateService(@PathVariable Long id, @RequestBody ServiceDTO dto) {
        ServiceDTO updated = serviceService.updateService(id, dto);
        return ResponseEntity.ok(updated);
    }
}
