package com.example.contruction.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "construction", name = "services")
public class ServiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "serviceEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ServiceTranslation> translations = new ArrayList<>();

    @Column(name = "public_id")
    private String publicId;

    @Column(name = "image_url")
    private String imageUrl;
}
