package com.example.contruction.controller;

import com.example.contruction.dto.ContactInfoDTO;
import com.example.contruction.service.ContactInfoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contact-info")
public class ContactInfoController {

    private final ContactInfoService contactInfoService;

    public ContactInfoController(ContactInfoService contactInfoService) {
        this.contactInfoService = contactInfoService;
    }

    @GetMapping
    public List<ContactInfoDTO> getAll() {
        return contactInfoService.getAll();
    }

    @PostMapping
    public ContactInfoDTO save(@RequestBody ContactInfoDTO dto) {
        return contactInfoService.saveOrUpdate(dto);
    }

    @PutMapping("/{id}")
    public ContactInfoDTO update(@PathVariable Long id, @RequestBody ContactInfoDTO dto) {
        dto.setId(id);
        return contactInfoService.saveOrUpdate(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        contactInfoService.delete(id);
    }
}
