package com.example.contruction.repository;

import com.example.contruction.entity.ServiceTranslation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ServiceTranslationRepository extends JpaRepository<ServiceTranslation, Long> {

    List<ServiceTranslation> findByLanguage(String language);

    Optional<ServiceTranslation> findByServiceEntity_IdAndLanguage(Long serviceId, String language);
}
