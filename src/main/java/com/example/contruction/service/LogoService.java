package com.example.contruction.service;

import com.example.contruction.entity.Logo;
import com.example.contruction.repository.LogoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class LogoService {

    private final LogoRepository logoRepository;
    private final ImageUploadService uploadService;

    public LogoService(LogoRepository logoRepository, ImageUploadService uploadService) {
        this.logoRepository = logoRepository;
        this.uploadService = uploadService;
    }

    public Logo getLogo() {
        return logoRepository.findAll().stream().findFirst().orElse(null);
    }

    public Logo saveOrUpdateLogo(MultipartFile file) throws IOException {
        Logo existing = getLogo();
        if (existing != null) {
            uploadService.deleteImage(existing.getPublicId());
            logoRepository.delete(existing);
        }

        Map uploadResult = uploadService.uploadImage(file, "construction-site/logo");
        Logo newLogo = new Logo(
                null,
                "logo",
                (String) uploadResult.get("secure_url"),
                (String) uploadResult.get("public_id")
        );

        return logoRepository.save(newLogo);
    }

    public void deleteLogo() throws IOException {
        List<Logo> logos = logoRepository.findAll();
        if (!logos.isEmpty()) {
            Logo logo = logos.get(0);
            uploadService.deleteImage(logo.getPublicId()); // cloudinary'den sil
            logoRepository.delete(logo); // DB'den sil
        }
    }

}
