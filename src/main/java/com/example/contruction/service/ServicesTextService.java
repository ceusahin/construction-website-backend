package com.example.contruction.service;

import com.example.contruction.dto.ServicesTextDTO;
import com.example.contruction.entity.ServicesText;
import com.example.contruction.exception.NotFoundException;
import com.example.contruction.repository.ServicesTextRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicesTextService {

    private final ServicesTextRepository repository;

    public ServicesTextService(ServicesTextRepository repository) {
        this.repository = repository;
    }

    public ServicesTextDTO getByLanguage(String languageCode) {
        ServicesText entity = repository.findByLanguageCode(languageCode)
                .orElseThrow(() -> new NotFoundException("Metin bulunamadı: " + languageCode));

        return toDTO(entity);
    }

    @Transactional
    public ServicesTextDTO updateOrCreate(String languageCode, ServicesTextDTO dto) {
        ServicesText entity = repository.findByLanguageCode(languageCode)
                .orElse(new ServicesText());

        entity.setLanguageCode(languageCode);
        entity.setH1(dto.h1());
        entity.setH2(dto.h2());
        entity.setParagraph(dto.paragraph());

        entity = repository.save(entity);

        return toDTO(entity);
    }

    private ServicesTextDTO toDTO(ServicesText entity) {
        return new ServicesTextDTO(
                entity.getId(),
                entity.getLanguageCode(),
                entity.getH1(),
                entity.getH2(),
                entity.getParagraph()
        );
    }
}

