package com.example.backend_service.service.account;


import com.example.backend_service.dto.request.account.UpdateProfileRequest;
import com.example.backend_service.dto.response.account.ProfileResponse;

public interface UserService {
    ProfileResponse getMyProfile(String currentUsername);
   ProfileResponse updateProfile(String currentUsername, UpdateProfileRequest req);
}
