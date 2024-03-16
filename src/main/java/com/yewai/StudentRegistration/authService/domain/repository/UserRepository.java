package com.yewai.StudentRegistration.authService.domain.repository;

import com.yewai.StudentRegistration.authService.domain.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String username);
}
