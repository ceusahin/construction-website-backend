package com.example.contruction.service;

import com.example.contruction.dto.AboutSectionDTO;
import com.example.contruction.entity.AboutSection;
import com.example.contruction.exception.NotFoundException;
import com.example.contruction.repository.AboutSectionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AboutSectionService {

    private final AboutSectionRepository repository;

    public AboutSectionService(AboutSectionRepository repository) {
        this.repository = repository;
    }

    public AboutSectionDTO getByLanguageAndSection(String languageCode, String section) {
        AboutSection entity = repository.findByLanguageCodeAndSection(languageCode, section)
                .orElseThrow(() -> new NotFoundException("Bölüm bulunamadı: " + languageCode + ", " + section));

        return toDTO(entity);
    }

    @Transactional
    public AboutSectionDTO updateOrCreate(String languageCode, String section, AboutSectionDTO dto) {
        AboutSection entity = repository.findByLanguageCodeAndSection(languageCode, section)
                .orElse(new AboutSection());

        entity.setLanguageCode(languageCode);
        entity.setSection(section);
        entity.setH1(dto.h1());
        entity.setH2(dto.h2());
        entity.setParagraph(dto.paragraph());
        entity.setImageUrl1(dto.imageUrl1());
        entity.setImageUrl2(dto.imageUrl2());
        entity.setPublicId1(dto.publicId1());
        entity.setPublicId2(dto.publicId2());
        entity.setYoutubeVideoId(dto.youtubeVideoId());

        entity = repository.save(entity);

        return toDTO(entity);
    }

    @Transactional
    public void updateVideoIdForAll(String videoId) {
        repository.updateYoutubeVideoIdForAll(videoId);
    }

    // DB'deki ilk bulunan video ID'yi getir (yoksa null)
    public String getVideoId() {
        return repository.findFirstVideoId();
    }

    private AboutSectionDTO toDTO(AboutSection entity) {
        return new AboutSectionDTO(
                entity.getId(),
                entity.getLanguageCode(),
                entity.getSection(),
                entity.getH1(),
                entity.getH2(),
                entity.getParagraph(),
                entity.getImageUrl1(),
                entity.getImageUrl2(),
                entity.getPublicId1(),
                entity.getPublicId2(),
                entity.getYoutubeVideoId()
        );
    }
}
