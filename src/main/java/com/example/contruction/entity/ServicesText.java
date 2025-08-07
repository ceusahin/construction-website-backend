package com.example.contruction.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "construction", name = "services_text", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"language_code"})
})
public class ServicesText {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "language_code", length = 2, nullable = false)
    private String languageCode;

    @Column(name = "h1", nullable = false)
    private String h1;

    @Column(name = "h2")
    private String h2;

    @Column(name = "paragraph", columnDefinition = "TEXT")
    private String paragraph;

}
