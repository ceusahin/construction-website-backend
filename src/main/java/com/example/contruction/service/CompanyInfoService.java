package com.example.contruction.service;

import com.example.contruction.entity.CompanyInfo;
import com.example.contruction.repository.CompanyInfoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyInfoService {

    private final CompanyInfoRepository repository;

    public CompanyInfoService(CompanyInfoRepository repository) {
        this.repository = repository;
    }

    public Optional<CompanyInfo> getCompanyInfo(Long id) {
        return repository.findById(id);
    }

    public CompanyInfo updateCompanyInfo(Long id, String newName, String newDescription) {
        CompanyInfo companyInfo = repository.findById(id).orElseThrow(() -> new RuntimeException("Company info not found"));
        companyInfo.setCompanyName(newName);
        companyInfo.setCompanyDescription(newDescription);
        return repository.save(companyInfo);
    }
}
