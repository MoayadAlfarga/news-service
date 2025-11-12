package com.appswaves.controllers;

import com.appswaves.dto.NewsDto;
import com.appswaves.service.NewsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/news")
@AllArgsConstructor
public class NewsController {
    private NewsService newsService;

    @PostMapping("/add")
    public ResponseEntity<NewsDto> addNewsEntity(@RequestBody NewsDto newsDto) {
        return new ResponseEntity<>(newsService.addNews(newsDto), HttpStatus.OK);
    }

    @GetMapping("/read")
    public ResponseEntity<NewsDto> addNewsEntity(@RequestParam Long newsId) {
        return new ResponseEntity<>(newsService.readNews(newsId), HttpStatus.OK);
    }

//    @GetMapping("/read-title")
//    public ResponseEntity<NewsDto> addNewsEntity(@RequestParam Long newsId) {
//        return new ResponseEntity<>(newsService.readNews(newsId), HttpStatus.OK);
//    }

    //    @GetMapping("/read-title-arabic")
//    public ResponseEntity<NewsDto> addNewsEntity(@RequestParam Long newsId) {
//        return new ResponseEntity<>(newsService.readNews(newsId), HttpStatus.OK);
//    }

    @PutMapping("/update")
    public ResponseEntity<NewsDto> addNewsEntity(@RequestParam Long newsId, @RequestBody NewsDto newsDto) {
        return new ResponseEntity<>(newsService.updateNews(newsId, newsDto), HttpStatus.OK);
    }
}
