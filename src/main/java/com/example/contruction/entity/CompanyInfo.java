package com.example.contruction.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "company_info", schema = "construction",
    uniqueConstraints = @UniqueConstraint(columnNames = {"language"}))
@Entity
public class CompanyInfo {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "company_description")
    private String companyDescription;

    @Column(name = "language", nullable = false)
    private String language;
}
