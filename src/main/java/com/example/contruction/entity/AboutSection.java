package com.example.contruction.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(schema = "construction", name = "about_section")
public class AboutSection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "language_code")
    private String languageCode;

    private String section;

    private String h1;
    private String h2;
    private String paragraph;

    @Column(name = "image_url_1")
    private String imageUrl1;

    @Column(name = "image_url_2")
    private String imageUrl2;

    @Column(name = "public_id_1")
    private String publicId1;

    @Column(name = "public_id_2")
    private String publicId2;

    @Column(name = "youtube_video_id")
    private String youtubeVideoId;

}
