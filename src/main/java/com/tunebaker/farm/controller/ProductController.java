package com.tunebaker.farm.controller;

import com.tunebaker.farm.model.dto.ProductDto;
import com.tunebaker.farm.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/new")
    public ResponseEntity<?> addNew(@RequestBody ProductDto productDto) {
        log.info("Получены данные продукта: {}" , productDto);
        productService.addNewProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
