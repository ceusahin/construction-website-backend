package com.example.contruction.dto;

import java.util.List;


public record ServiceDTO(
        Long id,
        List<ServiceTranslationDTO> translations,
        String publicId,
        String imageUrl
) {}
