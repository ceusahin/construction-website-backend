package com.example.contruction.controller;

import com.example.contruction.dto.FooterMenuDTO;
import com.example.contruction.entity.FooterMenu;
import com.example.contruction.service.FooterMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/footer-menu")
@RequiredArgsConstructor
public class FooterMenuController {

    private final FooterMenuService menuService;

    @GetMapping("/{language}")
    public ResponseEntity<List<FooterMenuDTO>> getMenusByLanguage(@PathVariable String language) {
        List<FooterMenuDTO> dtos = menuService.getMenuDTOsByLanguage(language);
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<FooterMenu> createMenu(@RequestBody FooterMenu menu) {
        return ResponseEntity.ok(menuService.saveMenu(menu));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FooterMenu> updateMenu(@PathVariable Long id, @RequestBody FooterMenu updatedMenu) {
        return menuService.getMenu(id)
                .map(menu -> {
                    updatedMenu.setId(id);
                    return ResponseEntity.ok(menuService.saveMenu(updatedMenu));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenu(@PathVariable Long id) {
        menuService.deleteMenu(id);
        return ResponseEntity.noContent().build();
    }
}
