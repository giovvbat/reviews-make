package com.giovanna.reviewsmake.service;

import com.giovanna.reviewsmake.dto.review.ReviewRecordDto;
import com.giovanna.reviewsmake.infra.exception.review.ReviewNotFoundException;
import com.giovanna.reviewsmake.infra.exception.user.UserNotFoundException;
import com.giovanna.reviewsmake.infra.exception.review.NoReviewsFoundException;
import com.giovanna.reviewsmake.infra.exception.product.ProductNotFoundException;
import com.giovanna.reviewsmake.model.ProductModel;
import com.giovanna.reviewsmake.model.ReviewModel;
import com.giovanna.reviewsmake.model.UserModel;
import com.giovanna.reviewsmake.repository.ProductRepository;
import com.giovanna.reviewsmake.repository.ReviewRepository;
import com.giovanna.reviewsmake.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReviewService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private UserRepository userRepository;

    public ReviewModel saveReview(ReviewRecordDto reviewRecordDto) {
        UserModel user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(UserNotFoundException::new);

        ProductModel product = productRepository.findById(reviewRecordDto.productId())
                .orElseThrow(ProductNotFoundException::new);

        var reviewModel = new ReviewModel();
        reviewModel.setReviewUser(user);
        reviewModel.setReviewProduct(product);
        reviewModel.setComment(reviewRecordDto.comment());

        return reviewRepository.save(reviewModel);
    }

    public List<ReviewModel> getAllReviews() {
        List<ReviewModel> reviews = reviewRepository.findAll();

        if (reviews.isEmpty()) {
            throw new NoReviewsFoundException();
        }

        return reviewRepository.findAll();
    }

    public ReviewModel getReview(UUID reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(ReviewNotFoundException::new);
    }

    public ReviewModel updateReview(UUID reviewId, ReviewRecordDto reviewRecordDto) {
        ReviewModel review = reviewRepository.findById(reviewId)
                .orElseThrow(ReviewNotFoundException::new);

        ProductModel product = productRepository.findById(reviewRecordDto.productId())
                .orElseThrow(ProductNotFoundException::new);

        UserModel user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(UserNotFoundException::new);

        review.setReviewProduct(product);
        review.setReviewUser(user);
        review.setComment(reviewRecordDto.comment());

        return reviewRepository.save(review);
    }

    public void deleteReview(UUID reviewId) {
        reviewRepository.findById(reviewId)
                .orElseThrow(ReviewNotFoundException::new);

        reviewRepository.deleteById(reviewId);
    }
}
