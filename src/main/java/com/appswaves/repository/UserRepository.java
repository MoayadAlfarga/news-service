package com.appswaves.repository;

import com.appswaves.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByEmail(String username);

    Optional<UserEntity> findByEmail(String email);

}
