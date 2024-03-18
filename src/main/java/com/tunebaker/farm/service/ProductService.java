package com.tunebaker.farm.service;

import com.tunebaker.farm.model.dto.ProductDto;


public interface ProductService {
    /**
     * Преобразует из Dto в сущность и вызывает метод добавления сведений о продукте
     * в хранилище
     * @param productDto данные о добавляемом продукте
     */
    void addNewProduct(ProductDto productDto);
}
