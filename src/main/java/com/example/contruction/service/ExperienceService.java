package com.example.contruction.service;

import com.example.contruction.dto.ExperienceDTO;
import java.util.List;

public interface ExperienceService {
    List<ExperienceDTO> getAll();
    ExperienceDTO saveOrUpdate(ExperienceDTO dto);
    void delete(Long id);
}
