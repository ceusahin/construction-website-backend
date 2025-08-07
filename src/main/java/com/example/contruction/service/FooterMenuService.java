package com.example.contruction.service;

import com.example.contruction.dto.FooterMenuDTO;
import com.example.contruction.dto.FooterMenuItemDTO;
import com.example.contruction.entity.FooterMenu;
import com.example.contruction.entity.FooterMenuItem;
import com.example.contruction.repository.FooterMenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FooterMenuService {

    private final FooterMenuRepository menuRepository;

    public List<FooterMenu> getMenusByLanguage(String language) {
        return menuRepository.findByLanguage(language);
    }

    public FooterMenu saveMenu(FooterMenu menu) {
        return menuRepository.save(menu);
    }

    public void deleteMenu(Long id) {
        menuRepository.deleteById(id);
    }

    public Optional<FooterMenu> getMenu(Long id) {
        return menuRepository.findById(id);
    }

    // Entity → DTO dönüşümü
    public FooterMenuDTO toDto(FooterMenu menu) {
        FooterMenuDTO dto = new FooterMenuDTO();
        dto.setId(menu.getId());
        dto.setLanguage(menu.getLanguage());
        dto.setTitle(menu.getTitle());

        List<FooterMenuItemDTO> itemDTOs = menu.getItems().stream()
                .map(this::toDto)
                .collect(Collectors.toList());

        dto.setItems(itemDTOs);
        return dto;
    }

    public FooterMenuItemDTO toDto(FooterMenuItem item) {
        FooterMenuItemDTO dto = new FooterMenuItemDTO();
        dto.setId(item.getId());
        dto.setName(item.getName());
        dto.setUrl(item.getUrl());
        dto.setSortOrder(item.getSortOrder());
        return dto;
    }

    public List<FooterMenuDTO> getMenuDTOsByLanguage(String language) {
        List<FooterMenu> menus = getMenusByLanguage(language);
        return menus.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
