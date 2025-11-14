package com.appswaves.controllers;

import com.appswaves.dto.NewsAdminDto;
import com.appswaves.dto.NewsDto;
import com.appswaves.dto.NewsUserDto;
import com.appswaves.service.NewsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news")
@AllArgsConstructor
public class NewsController {
    private NewsService newsService;

    @PostMapping("/add")
    public ResponseEntity<NewsDto> addNews(@RequestBody NewsDto newsDto) {
        return new ResponseEntity<>(newsService.addNews(newsDto), HttpStatus.OK);
    }

    @GetMapping("/read-admin")
    public ResponseEntity<NewsAdminDto> readAdminNews(@RequestParam Long newsId) {
        return new ResponseEntity<>(newsService.readNewsAdmin(newsId), HttpStatus.OK);
    }

    @GetMapping("/read")
    public ResponseEntity<NewsUserDto> readNews(@RequestParam Long newsId) {
        return new ResponseEntity<>(newsService.readNews(newsId), HttpStatus.OK);
    }

    @GetMapping("/read-all")
    public ResponseEntity<List<NewsUserDto>> readAllNews() {
        return new ResponseEntity<>(newsService.readAllNews(), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<NewsDto> updateStatusAndInformationNews(@RequestParam Long newsId, @RequestBody NewsDto newsDto) {
        return new ResponseEntity<>(newsService.updateNews(newsId, newsDto), HttpStatus.OK);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<?> removeNews(@RequestParam Long newsId) {
        return new ResponseEntity<>(newsService.deleteNews(newsId), HttpStatus.OK);
    }
}
