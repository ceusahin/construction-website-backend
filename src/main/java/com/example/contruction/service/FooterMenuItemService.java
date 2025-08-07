package com.example.contruction.service;

import com.example.contruction.dto.FooterMenuItemDTO;
import com.example.contruction.entity.FooterMenu;
import com.example.contruction.entity.FooterMenuItem;
import com.example.contruction.repository.FooterMenuItemRepository;
import com.example.contruction.repository.FooterMenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FooterMenuItemService {

    private final FooterMenuItemRepository itemRepository;
    private final FooterMenuRepository menuRepository;

    public FooterMenuItem addItemToMenu(Long menuId, FooterMenuItem item) {
        FooterMenu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new RuntimeException("Menu not found"));

        item.setMenu(menu);
        return itemRepository.save(item);
    }

    public FooterMenuItem updateItem(Long itemId, FooterMenuItem updatedItem) {
        FooterMenuItem existingItem = itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        existingItem.setName(updatedItem.getName());
        existingItem.setUrl(updatedItem.getUrl());
        existingItem.setSortOrder(updatedItem.getSortOrder());
        return itemRepository.save(existingItem);
    }

    public void deleteItem(Long itemId) {
        itemRepository.deleteById(itemId);
    }

    public List<FooterMenuItem> getItemsByMenuId(Long menuId) {
        return itemRepository.findByMenuIdOrderBySortOrderAsc(menuId);
    }

    // Entity → DTO dönüşümü
    public FooterMenuItemDTO toDto(FooterMenuItem item) {
        FooterMenuItemDTO dto = new FooterMenuItemDTO();
        dto.setId(item.getId());
        dto.setName(item.getName());
        dto.setUrl(item.getUrl());
        dto.setSortOrder(item.getSortOrder());
        return dto;
    }

    // Menü öğelerini DTO olarak getir
    public List<FooterMenuItemDTO> getItemDTOsByMenuId(Long menuId) {
        List<FooterMenuItem> items = getItemsByMenuId(menuId);
        return items.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
