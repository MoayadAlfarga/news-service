package com.appswaves.service;

import com.appswaves.dto.NewsAdminDto;
import com.appswaves.dto.NewsDto;
import com.appswaves.dto.NewsUserDto;
import com.appswaves.entity.NewsEntity;
import com.appswaves.entity.NewsStatusEntity;
import com.appswaves.enums.NewsStatus;
import com.appswaves.exceptions.DataRequiredException;
import com.appswaves.exceptions.NewsNotFoundException;
import com.appswaves.repository.NewsRepository;
import com.appswaves.repository.NewsStatusRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class NewsServiceImpl implements NewsService {
    private ObjectMapper objectMapper;
    private NewsRepository newsRepository;
    private NewsStatusRepository newsStatusRepository;

    private void saveStatus(NewsStatusEntity statusEntity) throws NewsNotFoundException {
        newsStatusRepository.save(statusEntity);
    }


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
        setStatusNewsAndSavePublish(newsEntity);
        newsEntity = saveNews(newsEntity);
        return mapToNewsDto(newsEntity);
    }


    private void setStatusNewsAndSavePublish(NewsEntity newsEntity) {
        NewsStatusEntity newsStatusEntity = new NewsStatusEntity();
        newsStatusEntity.setNewsStatus(NewsStatus.PENDING);
        newsEntity.setPublishDate(LocalDateTime.now());
        newsEntity.setNewsStatusEntity(newsStatusEntity);
        saveStatus(newsStatusEntity);
    }

    public Optional<NewsEntity> findNewsById(Long newsId) {
        Optional<NewsEntity> newsEntity = newsRepository.findById(newsId);
        if (newsEntity.isEmpty()) {
            throw new NewsNotFoundException();
        }
        return newsEntity;
    }

    @Override
    public NewsUserDto readNews(Long newsId) {
        NewsEntity newsEntity = this.findNewsById(newsId).orElseThrow();
        return buildReadNewsInformation(newsEntity);
    }

    @Override
    public NewsAdminDto readNewsAdmin(Long newsId) {
        checkIfNullOrEmpty(newsId);
        NewsEntity newsEntity = this.findNewsById(newsId).orElseThrow();
        return buildReadAdminNewsInformation(newsEntity);
    }

    public NewsAdminDto buildReadAdminNewsInformation(NewsEntity newsEntity) {
        return NewsAdminDto.builder()
                .id(newsEntity.getId())
                .titleArabic(newsEntity.getTitleArabic())
                .titleEnglish(newsEntity.getTitleEnglish())
                .descriptionArabic(newsEntity.getDescriptionArabic())
                .descriptionEnglish(newsEntity.getDescriptionEnglish())
                .publishDate(newsEntity.getPublishDate())
                .imageUrl(newsEntity.getImageUrl())
                .build();
    }


    public NewsUserDto buildReadNewsInformation(NewsEntity newsEntity) {
        return NewsUserDto.builder()
                .titleArabic(newsEntity.getTitleArabic())
                .titleEnglish(newsEntity.getTitleEnglish())
                .descriptionArabic(newsEntity.getDescriptionArabic())
                .descriptionEnglish(newsEntity.getDescriptionEnglish())
                .publishDate(newsEntity.getPublishDate())
                .build();
    }


    public void buildUpdateNewsInformation(NewsDto newsDto, NewsEntity newsEntity, NewsStatusEntity newsStatus) {
        newsEntity.setTitleEnglish(newsDto.getTitleEnglish());
        newsEntity.setTitleArabic(newsDto.getTitleArabic());
        newsEntity.setDescriptionEnglish(newsDto.getDescriptionEnglish());
        newsEntity.setDescriptionArabic(newsDto.getDescriptionArabic());
        newsEntity.setImageUrl(newsDto.getImageUrl());
        newsEntity.setNewsStatusEntity(newsStatus);
    }

    public NewsStatusEntity updateNewStatus() {
        NewsStatusEntity newsStatusEntity = new NewsStatusEntity();
        newsStatusEntity.setNewsStatus(NewsStatus.APPROVED);
        saveStatus(newsStatusEntity);
        return newsStatusEntity;
    }

    @Override
    public NewsDto updateNews(Long newsId, NewsDto newsDto) {
        log.info("Updating news. id={}, payload={}", newsId, newsDto);
        NewsEntity existing = findNewsById(newsId).orElseThrow(NewsNotFoundException::new);
        NewsStatusEntity newsStatusEntity = this.updateNewStatus();
        buildUpdateNewsInformation(newsDto, existing, newsStatusEntity);
        NewsEntity saved = saveNews(existing);
        log.info("The Data Updated and Save in Data Base {}", saved);
        return mapToNewsDto(saved);
    }

    public void delete(Long newsId) {
        newsRepository.deleteById(newsId);
    }

    @Override
    public String deleteNews(Long newsId) {
        checkIfNullOrEmpty(newsId);
        NewsEntity newsEntity = this.findNewsById(newsId).orElseThrow();
        delete(newsId);
        return "Success Delete News";
    }

    private void checkIfNullOrEmpty(Long newsId) {
        if (newsId == null) {
            throw new RuntimeException();
        }
    }

    @Override
    public List<NewsUserDto> readAllNews() {
        return newsRepository.findAll().stream()
                .map(r -> objectMapper.convertValue(r, NewsUserDto.class))
                .collect(Collectors.toList());
    }
}
