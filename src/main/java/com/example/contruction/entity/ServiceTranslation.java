package com.example.contruction.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(schema = "construction", name = "services_translation", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"service_id", "language"})
})
public class ServiceTranslation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private ServiceEntity serviceEntity;

    @Column(name = "language", nullable = false, length = 2)
    private String language;

    @Column(name = "service_name", nullable = false)
    private String serviceName;

    @Column(name = "service_description")
    private String serviceDescription;
}
