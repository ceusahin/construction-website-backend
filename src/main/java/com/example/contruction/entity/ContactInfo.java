package com.example.contruction.entity;

import com.example.contruction.enums.InfoType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contact_info", schema = "construction")
public class ContactInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private InfoType type;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @OneToMany(mappedBy = "contactInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ContactInfoTranslation> translations = new ArrayList<>();
}
