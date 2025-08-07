package com.example.contruction.dto;

public record AboutSectionDTO(
        Long id,
        String languageCode,
        String section,
        String h1,
        String h2,
        String paragraph,
        String imageUrl1,
        String imageUrl2,
        String publicId1,
        String publicId2,
        String youtubeVideoId
) {}
