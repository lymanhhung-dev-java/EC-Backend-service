package com.example.backend_service.service.product.impl;

import com.example.backend_service.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.math.BigDecimal;

public interface ProductService {

    Page<Product> getProducts(String search, Long categoryId, Long shopId, BigDecimal minPrice, BigDecimal maxPrice,
            Pageable pageable);

}
