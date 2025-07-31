package com.example.contruction.service;

import com.example.contruction.dto.ExperienceDTO;
import com.example.contruction.dto.ExperienceTranslationDTO;
import com.example.contruction.entity.Experience;
import com.example.contruction.entity.ExperienceTranslation;
import com.example.contruction.repository.ExperienceRepository;
import com.example.contruction.repository.ExperienceTranslationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExperienceServiceImpl implements ExperienceService {

    private final ExperienceRepository experienceRepository;
    private final ExperienceTranslationRepository translationRepository;

    public ExperienceServiceImpl(ExperienceRepository experienceRepository,
                                 ExperienceTranslationRepository translationRepository) {
        this.experienceRepository = experienceRepository;
        this.translationRepository = translationRepository;
    }

    @Override
    public List<ExperienceDTO> getAll() {
        return experienceRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ExperienceDTO saveOrUpdate(ExperienceDTO dto) {
        Experience experience = dto.getId() != null ?
                experienceRepository.findById(dto.getId()).orElse(new Experience()) :
                new Experience();

        experience.setVisible(dto.isVisible());

        Experience saved = experienceRepository.save(experience);

        translationRepository.deleteAll(translationRepository.findByExperienceId(saved.getId()));

        List<ExperienceTranslation> translations = dto.getTranslations().stream().map(t -> {
            ExperienceTranslation et = new ExperienceTranslation();
            et.setLanguage(t.getLanguage());
            et.setNumberText(t.getNumberText());
            et.setLabelText(t.getLabelText());
            et.setExperience(saved);
            return et;
        }).collect(Collectors.toList());

        translationRepository.saveAll(translations);

        return mapToDTO(saved);

    }

    @Override
    public void delete(Long id) {
        translationRepository.deleteAll(translationRepository.findByExperienceId(id));
        experienceRepository.deleteById(id);
    }

    private ExperienceDTO mapToDTO(Experience experience) {
        List<ExperienceTranslationDTO> translations = translationRepository.findByExperienceId(experience.getId()).stream()
                .map(t -> new ExperienceTranslationDTO(
                        t.getLanguage(),
                        t.getNumberText(),
                        t.getLabelText()
                ))
                .collect(Collectors.toList());

        return new ExperienceDTO(
                experience.getId(),
                experience.isVisible(),
                translations
        );
    }
}
