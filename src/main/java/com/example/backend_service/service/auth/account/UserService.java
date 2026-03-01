package com.example.backend_service.service.auth.account;

import com.example.backend_service.dto.request.account.UpdateProfileRequest;
import com.example.backend_service.dto.response.account.ProfileResponse;

public interface UserService {
    ProfileResponse updateProfile(String currentUsername, UpdateProfileRequest req);
    
}
