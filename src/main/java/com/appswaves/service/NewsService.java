package com.appswaves.service;

import com.appswaves.dto.NewsDto;
import com.appswaves.dto.NewsUserDto;

import java.util.List;
import java.util.Optional;

public interface NewsService {

     NewsDto addNews(NewsDto newsDto);

     List<NewsUserDto> readAllNews();

     NewsUserDto readNews(Long newsId);

     NewsDto updateNews(Long newsId, NewsDto newsDto);


}
