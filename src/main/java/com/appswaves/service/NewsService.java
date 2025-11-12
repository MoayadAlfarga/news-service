package com.appswaves.service;

import com.appswaves.dto.NewsDto;

import java.util.Optional;

public interface NewsService {

    public NewsDto addNews(NewsDto newsDto);

    public NewsDto readNews(Long newsId) ;

    public NewsDto updateNews(Long newsId, NewsDto newsDto);


}
