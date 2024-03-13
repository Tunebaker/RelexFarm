package com.tunebaker.farm.service;

import com.tunebaker.farm.model.dto.ProductDto;
import com.tunebaker.farm.model.entity.Product;
import com.tunebaker.farm.model.mapper.ProductMapper;
import com.tunebaker.farm.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    @Override
    public void addNewProduct(ProductDto productDto) {
        Product product = productMapper.fromProductDto(productDto);
        log.info("Продукт: {} был получен из productDto: {}", product, productDto);
        productRepository.save(product);
    }
}
