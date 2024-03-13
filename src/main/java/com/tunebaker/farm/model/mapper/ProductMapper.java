package com.tunebaker.farm.model.mapper;

import com.tunebaker.farm.model.dto.ProductDto;
import com.tunebaker.farm.model.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product fromProductDto(ProductDto productDto);
}
