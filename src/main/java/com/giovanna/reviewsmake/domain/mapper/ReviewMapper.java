package com.giovanna.reviewsmake.domain.mapper;

import com.giovanna.reviewsmake.domain.dto.review.ReviewRecordDto;
import com.giovanna.reviewsmake.domain.model.ReviewModel;
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
