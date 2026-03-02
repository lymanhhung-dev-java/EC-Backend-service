package com.example.backend_service.service.business;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.backend_service.commom.ShopStatus;
import com.example.backend_service.dto.request.business.RegisterShopRequest;
import com.example.backend_service.dto.response.business.ShopResponse;
import com.example.backend_service.dto.response.business.UpdateShopRequest;
import com.example.backend_service.model.business.Shop;

public interface ShopService {
   

    Page<ShopResponse> getShopsForAdmin(String keyword, ShopStatus status, Pageable pageable);
    void approveShope(Long id, Boolean isApproved);
    void banShop(Long shopId);
}
