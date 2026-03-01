package com.example.backend_service.service.business.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.backend_service.commom.ShopStatus;
import com.example.backend_service.dto.response.business.ShopResponse;
import com.example.backend_service.repository.ShopRepository;
import com.example.backend_service.service.business.ShopService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j(topic = "SHOP-SERVICE")
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService{
    private final ShopRepository shopRepository;

     @Override
    public Page<ShopResponse> getShopsForAdmin(String keyword, ShopStatus status, Pageable pageable) {
        return shopRepository.findAllByKeywordAndStatus(keyword, status, pageable)
                .map(ShopResponse::fromEntity);
    }
    
}
