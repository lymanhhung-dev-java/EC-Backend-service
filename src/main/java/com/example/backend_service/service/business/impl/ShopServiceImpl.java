package com.example.backend_service.service.business.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.backend_service.common.ShopStatus;
import com.example.backend_service.dto.response.business.ShopResponse;
import com.example.backend_service.exception.AppException;
import com.example.backend_service.model.auth.Role;
import com.example.backend_service.model.auth.User;
import com.example.backend_service.model.business.Shop;
import com.example.backend_service.repository.RoleRepository;
import com.example.backend_service.repository.ShopRepository;
import com.example.backend_service.repository.UserRepository;
import com.example.backend_service.service.business.ShopService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j(topic = "SHOP-SERVICE")
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService{
    final private UserRepository userRepository;
    private final ShopRepository shopRepository;
    
    private final RoleRepository roleRepository;

     @Override
    public Page<ShopResponse> getShopsForAdmin(String keyword, ShopStatus status, Pageable pageable) {
        return shopRepository.findAllByKeywordAndStatus(keyword, status, pageable)
                .map(ShopResponse::fromEntity);
    }

    @Override
    public void approveShope(Long shopId, Boolean isApproved) {

        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new AppException("Shop không tồn tại!"));
        if (isApproved) {
            shop.setStatus(ShopStatus.ACTIVE);
            User owner = shop.getOwner();
            Role sellerRole = roleRepository.findByName("SELLER")
                    .orElseThrow(() -> new AppException("Role SELLER not found"));
            owner.getRoles().add(sellerRole);
            userRepository.save(owner);

        } else {
            shop.setStatus(ShopStatus.REJECTED);
        }
        shopRepository.save(shop);
    }

    @Override
    public void banShop(Long shopId) {
        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new AppException("Shop không tồn tại"));
        shop.setStatus(ShopStatus.BANNED);
        shopRepository.save(shop);
    }
    
}
