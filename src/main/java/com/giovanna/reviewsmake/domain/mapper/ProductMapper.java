package com.giovanna.reviewsmake.domain.mapper;

import com.giovanna.reviewsmake.domain.model.ProductModel;
import com.giovanna.reviewsmake.domain.dto.product.ProductRecordDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper instance = Mappers.getMapper(ProductMapper.class);

    /*mapping from dto to model*/
    ProductModel toEntity(ProductRecordDto productRecordDto);

    /*mapping from model to dto*/
    ProductRecordDto toDto(ProductModel productModel);
}
