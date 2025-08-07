package com.example.contruction.dto;

import lombok.Data;

import java.util.List;

@Data
public class FooterMenuDTO {
    private Long id;
    private String language;
    private String title;
    private List<FooterMenuItemDTO> items;
}