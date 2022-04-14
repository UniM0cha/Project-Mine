package com.project.mine.service;

import java.util.Optional;
import java.util.UUID;

import com.project.mine.domain.User;
import com.project.mine.repository.UserRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public String getEmail(String userId) {
        User user = userRepository.findById(UUID.fromString(userId)).get();
        return user.getEmail();
    }
}
