package com.example.backend_service.controller.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/admin/products")
@RequiredArgsConstructor
@Tag(name = "Admin Product Controller", description = "Admin quản lý sản phẩm")

public class AdminProductController {

     private final AdminProductService adminProductService;
     @Operation(summary = "Khóa / Mở khóa sản phẩm", description = "Thay đổi trạng thái Active/Locked của sản phẩm")
    @PutMapping("/{id}/toggle-status")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> toggleProductStatus(@PathVariable Long id) {
        adminProductService.toggleProductStatus(id);
        return ResponseEntity.ok("Cập nhật trạng thái sản phẩm thành công!");
    }
    
}
