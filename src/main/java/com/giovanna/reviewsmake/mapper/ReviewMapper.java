package com.giovanna.reviewsmake.mapper;

import com.giovanna.reviewsmake.dto.product.ProductRecordDto;
import com.giovanna.reviewsmake.dto.review.ReviewRecordDto;
import com.giovanna.reviewsmake.model.ProductModel;
import com.giovanna.reviewsmake.model.ReviewModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReviewMapper {
    ReviewMapper instance = Mappers.getMapper(ReviewMapper.class);

    /*mapping from dto to model*/
    ReviewModel toEntity(ReviewRecordDto reviewRecordDto);

    /*mapping from model to dto*/
    ReviewRecordDto toDto(ReviewModel reviewModel);
}
