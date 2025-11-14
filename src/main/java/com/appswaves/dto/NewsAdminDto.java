package com.appswaves.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class NewsAdminDto {
    private Long id;
    private String titleEnglish;
    private String titleArabic;
    private String descriptionEnglish;
    private String descriptionArabic;
    private String imageUrl;
    private LocalDateTime publishDate;
}
