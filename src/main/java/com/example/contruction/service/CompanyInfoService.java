package com.example.contruction.service;

import com.example.contruction.entity.CompanyInfo;
import com.example.contruction.repository.CompanyInfoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service
public class CompanyInfoService {

    private final CompanyInfoRepository repository;

    public CompanyInfoService(CompanyInfoRepository repository) {
        this.repository = repository;
    }

    public CompanyInfo getCompanyInfoByLanguage(String language) {
        return repository.findByLanguage(language)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Company info not found for language: " + language));
    }

    public CompanyInfo updateCompanyInfoByLanguage(String language, CompanyInfo newInfo) {
        CompanyInfo existing = repository.findByLanguage(language)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Company info not found for language: " + language));

        existing.setCompanyName(newInfo.getCompanyName());
        existing.setCompanyDescription(newInfo.getCompanyDescription());

        return repository.save(existing);
    }

    public CompanyInfo createOrUpdateByLanguage(String language, CompanyInfo newInfo) {
        return repository.findByLanguage(language)
                .map(existing -> {
                    existing.setCompanyName(newInfo.getCompanyName());
                    existing.setCompanyDescription(newInfo.getCompanyDescription());
                    return repository.save(existing);
                })
                .orElseGet(() -> {
                    newInfo.setLanguage(language);
                    return repository.save(newInfo);
                });
    }
}
