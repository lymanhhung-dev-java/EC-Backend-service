package com.example.backend_service.dto.response.business;


import lombok.Data;

@Data
public class UpdateShopRequest {
    private String shopName;
    private String description;
    private String logoUrl;
    private String address;
}
