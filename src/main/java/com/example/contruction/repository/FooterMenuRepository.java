package com.example.contruction.repository;

import com.example.contruction.entity.FooterMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FooterMenuRepository extends JpaRepository<FooterMenu, Long> {
    List<FooterMenu> findByLanguage(String language);
}
