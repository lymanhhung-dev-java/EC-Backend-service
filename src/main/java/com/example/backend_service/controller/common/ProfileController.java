package com.example.backend_service.controller.common;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.backend_service.dto.response.account.ProfileResponse;
import com.example.backend_service.service.account.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/profile")
@Slf4j(topic = "PROFILE-CONTROLLER")
@Tag(name = "Profile Controller", description = "APIs for user profile management")
public class ProfileController {
      
    private final UserService userService;

    @Operation(summary = "Get My Profile", description = "Lấy thông tin chi tiết của người dùng đang đăng nhập")
    @GetMapping("/me")
    public ResponseEntity<ProfileResponse> getMyProfile(){
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("Request get profile for user: {}", currentUsername);
        return ResponseEntity.ok(userService.getMyProfile(currentUsername));
    }
}
