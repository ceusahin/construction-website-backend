package com.example.contruction.repository;

import com.example.contruction.entity.SliderTranslation;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SliderTranslationRepository extends JpaRepository<SliderTranslation, Long> {
    Optional<SliderTranslation> findBySliderIdAndLanguage(Long sliderId, String language);
    List<SliderTranslation> findAllByLanguage(String language);
    List<SliderTranslation> findBySliderId(Long sliderId);

    @Modifying
    @Transactional
    @Query("DELETE FROM SliderTranslation t WHERE t.slider.id = :sliderId")
    void deleteBySliderId(Long sliderId);
}