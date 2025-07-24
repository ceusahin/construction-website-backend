package com.example.contruction.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "slider_translation", schema = "construction", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"slider_id", "language"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SliderTranslation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "slider_id", nullable = false)
    private Slider slider;

    @Column(name = "language", nullable = false, length = 2)
    private String language;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "button1_text")
    private String button1Text;

    @Column(name = "button2_text")
    private String button2Text;
}
