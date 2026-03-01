package com.example.backend_service.service.account.impl;

import org.springframework.stereotype.Service;

import com.example.backend_service.dto.response.account.ProfileResponse;
import com.example.backend_service.exception.AppException;
import com.example.backend_service.model.auth.User;
import com.example.backend_service.repository.UserRepository;
import com.example.backend_service.service.account.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "USER-SERVICE")
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    @Override
    public ProfileResponse getMyProfile(String currentUsername) {
      
        User user = getUserByUsername(currentUsername);
        return ProfileResponse.fromUser(user);
    }

     private User getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new AppException("User not found");
        }
        return user;
    }
    
}
