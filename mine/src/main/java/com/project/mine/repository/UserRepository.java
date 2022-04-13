package com.project.mine.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.project.mine.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findById(UUID id);
}