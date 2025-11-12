package com.appswaves.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "news_information")
@Builder
public class NewsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titleEnglish;
    private String titleArabic;
    private String descriptionEnglish;
    private String descriptionArabic;
    private String imageUrl;
    private String publishDate;
}
