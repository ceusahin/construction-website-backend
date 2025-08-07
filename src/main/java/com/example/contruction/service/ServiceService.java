package com.example.contruction.service;

import com.example.contruction.dto.ServiceDTO;
import com.example.contruction.dto.ServiceTranslationDTO;
import com.example.contruction.entity.ServiceEntity;
import com.example.contruction.entity.ServiceTranslation;
import com.example.contruction.repository.ServiceEntityRepository;
import com.example.contruction.repository.ServiceTranslationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServiceService {

    private final ServiceEntityRepository serviceRepository;
    private final ServiceTranslationRepository translationRepository;

    public ServiceService(ServiceEntityRepository serviceRepository, ServiceTranslationRepository translationRepository) {
        this.serviceRepository = serviceRepository;
        this.translationRepository = translationRepository;
    }

    // Site kullanıcısı için dil seçimine göre servisleri döner (her servis sadece seçilen dilin çevirisi ile, imageUrl ve publicId dahil)
    public List<ServiceDTO> getServicesByLanguageWithImages(String languageCode) {
        // Tüm servisleri çek
        List<ServiceEntity> allServices = serviceRepository.findAll();

        // Her servisi seçilen dile göre maple
        return allServices.stream().map(service -> {
            // İlgili dildeki çeviri
            List<ServiceTranslationDTO> filteredTranslations = service.getTranslations().stream()
                    .filter(t -> t.getLanguage().equals(languageCode))
                    .map(t -> new ServiceTranslationDTO(t.getLanguage(), t.getServiceName(), t.getServiceDescription()))
                    .toList();

            return new ServiceDTO(
                    service.getId(),
                    filteredTranslations,
                    service.getPublicId(),
                    service.getImageUrl()
            );
        }).toList();
    }

    // Admin için tüm servisleri dillerle ve resim alanları ile getir (değişmedi)
    public List<ServiceDTO> getAllServicesForAdmin() {
        return serviceRepository.findAll().stream().map(service ->
                new ServiceDTO(
                        service.getId(),
                        service.getTranslations().stream().map(t -> new ServiceTranslationDTO(
                                t.getLanguage(),
                                t.getServiceName(),
                                t.getServiceDescription()
                        )).toList(),
                        service.getPublicId(),
                        service.getImageUrl()
                )
        ).toList();
    }

    // Yeni servis ekleme (resim alanları da eklenebilir)
    @Transactional
    public ServiceDTO addService(ServiceDTO dto) {
        ServiceEntity service = new ServiceEntity();
        service.setPublicId(dto.publicId());
        service.setImageUrl(dto.imageUrl());

        List<ServiceTranslation> translations = dto.translations().stream()
                .map(t -> {
                    ServiceTranslation tr = new ServiceTranslation();
                    tr.setServiceEntity(service);
                    tr.setLanguage(t.languageCode());
                    tr.setServiceName(t.serviceName());
                    tr.setServiceDescription(t.serviceDescription());
                    return tr;
                }).toList();

        service.setTranslations(translations);
        ServiceEntity saved = serviceRepository.save(service);

        return new ServiceDTO(saved.getId(), dto.translations(), saved.getPublicId(), saved.getImageUrl());
    }

    // ID + dil bazlı tek çeviri döndürme (site kullanıcı için)
    public ServiceTranslationDTO getTranslationByServiceIdAndLanguage(Long serviceId, String lang) {
        return translationRepository.findByServiceEntity_IdAndLanguage(serviceId, lang)
                .map(t -> new ServiceTranslationDTO(
                        t.getLanguage(),
                        t.getServiceName(),
                        t.getServiceDescription()
                ))
                .orElseThrow(() -> new NoSuchElementException("Service translation not found"));
    }

    // Servis silme
    @Transactional
    public void deleteService(Long id) {
        serviceRepository.deleteById(id);
    }

    @Transactional
    public ServiceDTO updateService(Long id, ServiceDTO dto) {
        ServiceEntity service = serviceRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Service not found"));

        // Resim alanlarını güncelle
        service.setPublicId(dto.publicId());
        service.setImageUrl(dto.imageUrl());

        // Çevirileri güncelle veya ekle
        for (ServiceTranslationDTO trDto : dto.translations()) {
            Optional<ServiceTranslation> existing = translationRepository.findByServiceEntity_IdAndLanguage(id, trDto.languageCode());
            if (existing.isPresent()) {
                ServiceTranslation tr = existing.get();
                tr.setServiceName(trDto.serviceName());
                tr.setServiceDescription(trDto.serviceDescription());
                translationRepository.save(tr);
            } else {
                ServiceTranslation tr = new ServiceTranslation();
                tr.setServiceEntity(service);
                tr.setLanguage(trDto.languageCode());
                tr.setServiceName(trDto.serviceName());
                tr.setServiceDescription(trDto.serviceDescription());
                translationRepository.save(tr);
            }
        }

        serviceRepository.save(service);

        return new ServiceDTO(
                service.getId(),
                service.getTranslations().stream()
                        .map(t -> new ServiceTranslationDTO(t.getLanguage(), t.getServiceName(), t.getServiceDescription()))
                        .toList(),
                service.getPublicId(),
                service.getImageUrl()
        );
    }
}
