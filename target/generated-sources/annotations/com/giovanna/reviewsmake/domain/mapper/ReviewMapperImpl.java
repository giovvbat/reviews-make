package com.giovanna.reviewsmake.domain.mapper;

import com.giovanna.reviewsmake.domain.dto.review.ReviewRecordDto;
import com.giovanna.reviewsmake.domain.model.ReviewModel;
import java.util.UUID;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-10T11:52:08-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
public class ReviewMapperImpl implements ReviewMapper {

    @Override
    public ReviewModel toEntity(ReviewRecordDto reviewRecordDto) {
        if ( reviewRecordDto == null ) {
            return null;
        }

        ReviewModel reviewModel = new ReviewModel();

        reviewModel.setComment( reviewRecordDto.comment() );

        return reviewModel;
    }

    @Override
    public ReviewRecordDto toDto(ReviewModel reviewModel) {
        if ( reviewModel == null ) {
            return null;
        }

        String comment = null;

        comment = reviewModel.getComment();

        UUID productId = null;

        ReviewRecordDto reviewRecordDto = new ReviewRecordDto( productId, comment );

        return reviewRecordDto;
    }
}
