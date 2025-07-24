package com.example.contruction.service;

import com.example.contruction.dto.SliderDTO;
import com.example.contruction.dto.SliderTranslationDTO;
import com.example.contruction.entity.Slider;
import com.example.contruction.entity.SliderTranslation;
import com.example.contruction.repository.SliderRepository;
import com.example.contruction.repository.SliderTranslationRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SliderService {

    private final SliderRepository sliderRepository;
    private final SliderTranslationRepository translationRepository;

    public SliderService(SliderRepository sliderRepository, SliderTranslationRepository translationRepository) {
        this.sliderRepository = sliderRepository;
        this.translationRepository = translationRepository;
    }

    public List<SliderDTO> getSlidersByLanguage(String language) {
        List<Slider> sliders = sliderRepository.findAll();
        List<SliderDTO> dtos = new ArrayList<>();

        for (Slider slider : sliders) {
            SliderDTO dto = new SliderDTO();
            dto.setId(slider.getId());
            dto.setImageUrl(slider.getImageUrl());
            dto.setButton1Url(slider.getButton1Url());
            dto.setButton2Url(slider.getButton2Url());

            Map<String, SliderTranslationDTO> translationsMap = new HashMap<>();

            List<SliderTranslation> translations = translationRepository.findBySliderId(slider.getId());
            for (SliderTranslation tr : translations) {
                SliderTranslationDTO trDto = new SliderTranslationDTO();
                trDto.setTitle(tr.getTitle());
                trDto.setDescription(tr.getDescription());
                trDto.setButton1Text(tr.getButton1Text());
                trDto.setButton2Text(tr.getButton2Text());
                translationsMap.put(tr.getLanguage(), trDto);
            }

            dto.setTranslations(translationsMap);
            dtos.add(dto);
        }

        return dtos;
    }

    @Transactional
    @Modifying
    public SliderDTO saveOrUpdateSlider(SliderDTO dto) {
        Slider sliderEntity = (dto.getId() != null)
                ? sliderRepository.findById(dto.getId()).orElse(new Slider())
                : new Slider();

        sliderEntity.setImageUrl(dto.getImageUrl());
        sliderEntity.setButton1Url(dto.getButton1Url());
        sliderEntity.setButton2Url(dto.getButton2Url());
        sliderEntity = sliderRepository.save(sliderEntity);

        translationRepository.deleteBySliderId(sliderEntity.getId());

        // Yeni çevirileri ekle
        if (dto.getTranslations() != null) {
            Slider finalSlider = sliderEntity;
            dto.getTranslations().forEach((lang, translationDTO) -> {
                SliderTranslation translation = new SliderTranslation();
                translation.setSlider(finalSlider);
                translation.setLanguage(lang);
                translation.setTitle(translationDTO.getTitle());
                translation.setDescription(translationDTO.getDescription());
                translation.setButton1Text(translationDTO.getButton1Text());
                translation.setButton2Text(translationDTO.getButton2Text());
                translationRepository.save(translation);
            });
        }

        dto.setId(sliderEntity.getId());
        return dto;
    }

    @Transactional
    public void deleteSlider(Long sliderId) {
        translationRepository.deleteBySliderId(sliderId);
        sliderRepository.deleteById(sliderId);
    }


}
