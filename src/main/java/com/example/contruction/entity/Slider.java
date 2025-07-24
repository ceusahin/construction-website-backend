package com.example.contruction.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "slider", schema = "construction")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Slider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "button1_url")
    private String button1Url;

    @Column(name = "button2_url")
    private String button2Url;

    @OneToMany(mappedBy = "slider", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<SliderTranslation> translations;
}

