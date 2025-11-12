package com.appswaves.service;

import com.appswaves.dto.NewsDto;
import com.appswaves.entity.NewsEntity;
import com.appswaves.exceptions.NewsNotFoundException;
import com.appswaves.repository.NewsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class NewsServiceImpl implements NewsService {
    private  ObjectMapper objectMapper;
    private    NewsRepository newsRepository;

    private NewsEntity saveNews(NewsEntity newsEntity) {
        return newsRepository.save(newsEntity);
    }

    public NewsEntity mapToNewsEntity(NewsDto newsDto) {
        return objectMapper.convertValue(newsDto, NewsEntity.class);
    }

    public NewsDto mapToNewsDto(NewsEntity newsEntity) {
        return objectMapper.convertValue(newsEntity, NewsDto.class);
    }


    @Override
    public NewsDto addNews(NewsDto newsDto) {
        NewsEntity newsEntity = mapToNewsEntity(newsDto);
        newsEntity = saveNews(newsEntity);
        return mapToNewsDto(newsEntity);
    }

    public Optional<NewsEntity> findNewsById(Long newsId) {
        Optional<NewsEntity> newsEntity = newsRepository.findById(newsId);
        if (newsEntity.isEmpty()) {
            throw new NewsNotFoundException();
        }
        return newsEntity;
    }

    @Override
    public NewsDto readNews(Long newsId) {
        NewsEntity newsEntity = this.findNewsById(newsId)
                .orElseThrow();
        return mapToNewsDto(newsEntity);
    }

    public NewsEntity buildUpdateNewsInformation(NewsDto newsDto, NewsEntity newsEntity) {
        newsEntity.setTitleEnglish(newsDto.getTitleEnglish());
        newsEntity.setTitleArabic(newsDto.getTitleArabic());
        newsEntity.setDescriptionEnglish(newsDto.getDescriptionEnglish());
        newsEntity.setDescriptionArabic(newsDto.getDescriptionArabic());
        newsEntity.setImageUrl(newsDto.getImageUrl());
        return newsEntity;
    }

    @Override
    public NewsDto updateNews(Long newsId, NewsDto newsDto) {
        log.info("Updating news. id={}, payload={}", newsId, newsDto);
        NewsEntity existing = findNewsById(newsId).orElseThrow(NewsNotFoundException::new);
        buildUpdateNewsInformation(newsDto, existing);
        NewsEntity saved = saveNews(existing);
        log.info("The Data Updated and Save in Data Base {}", saved);
        return mapToNewsDto(saved);
    }
}
