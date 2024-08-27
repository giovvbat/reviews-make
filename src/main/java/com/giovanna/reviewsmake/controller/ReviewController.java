package com.giovanna.reviewsmake.controller;

import com.giovanna.reviewsmake.dto.ReviewRecordDto;
import com.giovanna.reviewsmake.model.ProductModel;
import com.giovanna.reviewsmake.model.ReviewModel;
import com.giovanna.reviewsmake.model.UserModel;
import com.giovanna.reviewsmake.repository.ProductRepository;
import com.giovanna.reviewsmake.repository.ReviewRepository;
import com.giovanna.reviewsmake.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<Object> saveReview(@RequestBody @Valid ReviewRecordDto reviewRecordDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Optional<UserModel> user = userRepository.findByUsername(userDetails.getUsername());
        var product = productRepository.findById(reviewRecordDto.productId());
        var reviewModel = new ReviewModel();

        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
        }

        if (product.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found!");
        }

        reviewModel.setReviewUser(user.get());
        reviewModel.setReviewProduct(product.get());
        reviewModel.setComment(reviewRecordDto.comment());

        return ResponseEntity.status(HttpStatus.CREATED).body(reviewRepository.save(reviewModel));
    }

    @GetMapping
    public ResponseEntity<Object> getAllReviews() {
        List<ReviewModel> reviews = reviewRepository.findAll();

        if (reviews.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No reviews found!");
        }

        return ResponseEntity.status(HttpStatus.OK).body(reviewRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getReview(@PathVariable(value="id") UUID reviewId) {
        Optional<ReviewModel> review = reviewRepository.findById(reviewId);

        if(review.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Review not found!");
        }

        return ResponseEntity.status(HttpStatus.OK).body(review.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateReview(@PathVariable(value="id") UUID reviewId, @RequestBody @Valid ReviewRecordDto reviewRecordDto) {
        Optional<ReviewModel> review = reviewRepository.findById(reviewId);
        Optional<ProductModel> product = productRepository.findById(reviewRecordDto.productId());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Optional<UserModel> user = userRepository.findByUsername(userDetails.getUsername());

        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
        }

        if (product.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found!");
        }

        if (review.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Review not found!");
        }

        var reviewModel = review.get();
        reviewModel.setReviewProduct(product.get());
        reviewModel.setReviewUser(user.get());
        reviewModel.setComment(reviewRecordDto.comment());

        return ResponseEntity.status(HttpStatus.OK).body(reviewRepository.save(reviewModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable(value="id") UUID reviewId) {
        Optional<ReviewModel> review = reviewRepository.findById(reviewId);

        if (review.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Review not found!");
        }

        reviewRepository.deleteById(reviewId);
        return ResponseEntity.status(HttpStatus.OK).body("Review deleted!");
    }

}
