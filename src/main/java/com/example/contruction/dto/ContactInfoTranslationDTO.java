package com.example.contruction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContactInfoTranslationDTO {
    private String language;
    private String title;
    private String content;
}
