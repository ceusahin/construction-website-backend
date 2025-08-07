package com.example.contruction.repository;

import com.example.contruction.entity.AboutSection;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AboutSectionRepository extends JpaRepository<AboutSection, Long> {

    Optional<AboutSection> findByLanguageCodeAndSection(String languageCode, String section);

    @Transactional
    @Modifying
    @Query("UPDATE AboutSection a SET a.youtubeVideoId = :videoId")
    int updateYoutubeVideoIdForAll(@Param("videoId") String videoId);

    @Query(value = "SELECT youtube_video_id FROM construction.about_section WHERE youtube_video_id IS NOT NULL LIMIT 1", nativeQuery = true)
    Optional<String> findFirstVideoIdOptional();

    default String findFirstVideoId() {
        return findFirstVideoIdOptional().orElse(null);
    }
}
