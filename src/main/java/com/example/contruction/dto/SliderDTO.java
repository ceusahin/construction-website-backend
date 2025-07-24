package com.example.contruction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SliderDTO {
    private Long id;
    private String imageUrl;
    private String button1Url;
    private String button2Url;

    private Map<String, SliderTranslationDTO> translations;
}
