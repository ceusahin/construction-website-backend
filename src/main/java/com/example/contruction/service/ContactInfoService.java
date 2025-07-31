package com.example.contruction.service;

import com.example.contruction.dto.ContactInfoDTO;
import com.example.contruction.dto.ContactInfoTranslationDTO;
import com.example.contruction.entity.ContactInfo;
import com.example.contruction.entity.ContactInfoTranslation;
import com.example.contruction.repository.ContactInfoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactInfoService {

    private final ContactInfoRepository contactInfoRepository;

    public ContactInfoService(ContactInfoRepository contactInfoRepository) {
        this.contactInfoRepository = contactInfoRepository;
    }

    public List<ContactInfoDTO> getAll() {
        return contactInfoRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public ContactInfoDTO saveOrUpdate(ContactInfoDTO dto) {
        ContactInfo entity = dto.getId() != null
                ? contactInfoRepository.findById(dto.getId()).orElse(new ContactInfo())
                : new ContactInfo();

        entity.setType(dto.getType());
        entity.setIsActive(dto.getIsActive());

        // Clear old translations if updating
        entity.getTranslations().clear();

        // Add new translations
        for (ContactInfoTranslationDTO transDto : dto.getTranslations()) {
            ContactInfoTranslation translation = new ContactInfoTranslation();
            translation.setLanguage(transDto.getLanguage());
            translation.setTitle(transDto.getTitle());
            translation.setContent(transDto.getContent());
            translation.setContactInfo(entity);
            entity.getTranslations().add(translation);
        }

        return mapToDTO(contactInfoRepository.save(entity));
    }

    public void delete(Long id) {
        contactInfoRepository.deleteById(id);
    }

    private ContactInfoDTO mapToDTO(ContactInfo entity) {
        ContactInfoDTO dto = new ContactInfoDTO();
        dto.setId(entity.getId());
        dto.setType(entity.getType());
        dto.setIsActive(entity.getIsActive());

        List<ContactInfoTranslationDTO> translations = entity.getTranslations().stream()
                .map(t -> {
                    ContactInfoTranslationDTO tdto = new ContactInfoTranslationDTO();
                    tdto.setLanguage(t.getLanguage());
                    tdto.setTitle(t.getTitle());
                    tdto.setContent(t.getContent());
                    return tdto;
                })
                .collect(Collectors.toList());

        dto.setTranslations(translations);
        return dto;
    }
}
