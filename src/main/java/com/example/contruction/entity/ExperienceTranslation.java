package com.example.contruction.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "experience_translation", schema = "construction")
public class ExperienceTranslation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String language;

    @Column(name = "number_text")
    private String numberText;

    @Column(name = "label_text")
    private String labelText;

    @ManyToOne
    @JoinColumn(name = "experience_id")
    private Experience experience;
}
