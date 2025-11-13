package com.appswaves.repository;

import com.appswaves.entity.NewsEntity;
import com.appswaves.entity.NewsStatusEntity;
import com.appswaves.enums.NewsStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.nio.file.LinkOption;

@Repository
public interface NewsStatusRepository extends JpaRepository<NewsStatusEntity, Long> {

}
