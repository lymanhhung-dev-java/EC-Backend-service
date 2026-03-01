package com.example.backend_service.service.auth.account.impl;

import org.springframework.stereotype.Service;

import com.example.backend_service.dto.request.account.UpdateProfileRequest;
import com.example.backend_service.dto.response.account.ProfileResponse;
import com.example.backend_service.exception.AppException;
import com.example.backend_service.model.auth.User;
import com.example.backend_service.repository.UserRepository;
import com.example.backend_service.service.auth.account.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Service
@RequiredArgsConstructor
@Slf4j(topic = "USER-SERVICE")
public class UserServiceImpl implements UserService {
        private final UserRepository userRepository;
    
    @Override
    public ProfileResponse updateProfile(String currentUsername, UpdateProfileRequest req) {
        User user = getUserByUsername(currentUsername);

        if (req.getFullName() != null && !req.getFullName().isBlank()) {
            user.setFullName(req.getFullName());
        }
        if (req.getPhoneNumber() != null && !req.getPhoneNumber().isBlank()) {
            user.setPhoneNumber(req.getPhoneNumber());
        }
        if (req.getAvatarUrl() != null && !req.getAvatarUrl().isBlank()) {
            user.setAvatarUrl(req.getAvatarUrl());
        }

        User updatedUser = userRepository.save(user);
        return ProfileResponse.fromUser(updatedUser);
    }
    private User getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new AppException("User not found");
        }
        return user;
    }

}
