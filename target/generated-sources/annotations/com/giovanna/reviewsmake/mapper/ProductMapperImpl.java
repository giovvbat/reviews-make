package com.giovanna.reviewsmake.mapper;

import com.giovanna.reviewsmake.dto.product.ProductRecordDto;
import com.giovanna.reviewsmake.model.ProductModel;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-25T21:46:05-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductModel toEntity(ProductRecordDto productRecordDto) {
        if ( productRecordDto == null ) {
            return null;
        }

        ProductModel productModel = new ProductModel();

        productModel.setName( productRecordDto.name() );
        productModel.setValue( productRecordDto.value() );
        productModel.setBrand( productRecordDto.brand() );

        return productModel;
    }

    @Override
    public ProductRecordDto toDto(ProductModel productModel) {
        if ( productModel == null ) {
            return null;
        }

        String name = null;
        BigDecimal value = null;
        String brand = null;

        name = productModel.getName();
        value = productModel.getValue();
        brand = productModel.getBrand();

        ProductRecordDto productRecordDto = new ProductRecordDto( name, value, brand );

        return productRecordDto;
    }
}
