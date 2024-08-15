package com.giovanna.projectsti.controller;

import com.giovanna.projectsti.dto.ReviewRecordDto;
import com.giovanna.projectsti.model.ProductModel;
import com.giovanna.projectsti.model.ReviewModel;
import com.giovanna.projectsti.model.UserModel;
import com.giovanna.projectsti.repository.ProductRepository;
import com.giovanna.projectsti.repository.ReviewRepository;
import com.giovanna.projectsti.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        var reviewModel = new ReviewModel();
        var user = userRepository.findById(reviewRecordDto.username());
        var product = productRepository.findById(reviewRecordDto.productId());

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

    @GetMapping("products/{productId}")
    public ResponseEntity<Object> getAllReviewsByProduct(@PathVariable(value = "productId") UUID productId) {
        Optional<ProductModel> product = productRepository.findById(productId);

        if(product.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found!");
        }

        return ResponseEntity.status(HttpStatus.OK).body(reviewRepository.findAllByReviewProduct(product.get()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateReview(@PathVariable(value="id") UUID reviewId, @RequestBody @Valid ReviewRecordDto reviewRecordDto) {
        Optional<ReviewModel> review = reviewRepository.findById(reviewId);
        Optional<UserModel> user = userRepository.findById(reviewRecordDto.username());
        Optional<ProductModel> product = productRepository.findById(reviewRecordDto.productId());

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
