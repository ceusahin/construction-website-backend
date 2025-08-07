package com.example.contruction.dto;

import lombok.Data;

@Data
public class FooterMenuItemDTO {
    private Long id;
    private String name;
    private String url;
    private Integer sortOrder;
}
