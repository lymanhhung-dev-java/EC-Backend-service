package com.example.backend_service.dto.response.product;

public class ProductResponse {
    
}
package com.example.backend_service.dto.response.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private Long id;
    private String name;
    private Double price;
    private String description;
    private String imageUrl; 
    private Integer stock;

   
    public ProductResponse(Product entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.price = entity.getPrice();
        this.description = entity.getDescription();
        this.imageUrl = entity.getImageUrl();
        this.stock = entity.getStock();
    }
}