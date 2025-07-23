package com.example.contruction.repository;
import com.example.contruction.entity.GalleryImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GalleryRepository extends JpaRepository<GalleryImage, Long> {
}
