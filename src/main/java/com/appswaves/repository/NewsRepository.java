package com.appswaves.repository;

import com.appswaves.entity.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<NewsEntity, Long> {

//    NewsEntity findByTitleArabic(Long id);
}
