package com.appswaves.service;

import com.appswaves.dto.NewsDto;

import java.util.List;
import java.util.Optional;

public interface NewsService {

     NewsDto addNews(NewsDto newsDto);

     List<NewsDto> readAllNews();

     NewsDto readNews(Long newsId);

     NewsDto updateNews(Long newsId, NewsDto newsDto);


}
