package com.appswaves.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class NewsDto {
    private String titleEnglish;
    private String titleArabic;
    private String descriptionEnglish;
    private String descriptionArabic;
    private String imageUrl;
    private String publishDate;
//    private String status;
}
