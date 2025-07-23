package com.example.contruction.service;

import com.example.contruction.entity.Favicon;
import com.example.contruction.repository.FaviconRepository;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class FaviconService {

    private final FaviconRepository faviconRepository;
    private final ImageUploadService uploadService;

    public FaviconService(FaviconRepository faviconRepository, ImageUploadService uploadService) {
        this.faviconRepository = faviconRepository;
        this.uploadService = uploadService;
    }

    public Favicon getFavicon() {
        return faviconRepository.findAll().stream().findFirst().orElse(null);
    }

    public Favicon saveOrUpdateFavicon(MultipartFile file) throws IOException {
        Favicon existing = getFavicon();
        if (existing != null) {
            uploadService.deleteImage(existing.getPublicId());
            faviconRepository.delete(existing);
        }

        Map uploadResult = uploadService.uploadImage(file, "construction-site/favicon");

        Favicon newFavicon = new Favicon(
                null,
                "favicon",
                (String) uploadResult.get("secure_url"),
                (String) uploadResult.get("public_id")
        );

        return faviconRepository.save(newFavicon);
    }

    public void deleteFavicon() throws IOException {
        List<Favicon> favicons = faviconRepository.findAll();
        if (!favicons.isEmpty()) {
            Favicon favicon = favicons.get(0);
            uploadService.deleteImage(favicon.getPublicId()); // cloudinary'den sil
            faviconRepository.delete(favicon); // DB'den sil
        }
    }

}
