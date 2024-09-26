package com.giovanna.reviewsmake.mapper;

import com.giovanna.reviewsmake.dto.product.ProductRecordDto;
import com.giovanna.reviewsmake.model.ProductModel;
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
