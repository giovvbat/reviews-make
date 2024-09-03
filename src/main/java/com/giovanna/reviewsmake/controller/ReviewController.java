package com.giovanna.reviewsmake.controller;

import com.giovanna.reviewsmake.dto.ReviewRecordDto;
import com.giovanna.reviewsmake.model.ProductModel;
import com.giovanna.reviewsmake.model.ReviewModel;
import com.giovanna.reviewsmake.model.UserModel;
import com.giovanna.reviewsmake.repository.ProductRepository;
import com.giovanna.reviewsmake.repository.ReviewRepository;
import com.giovanna.reviewsmake.repository.UserRepository;
import com.giovanna.reviewsmake.service.ReviewService;
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
    ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Object> saveReview(@RequestBody @Valid ReviewRecordDto reviewRecordDto) {
        return reviewService.saveReview(reviewRecordDto);
    }

    @GetMapping
    public ResponseEntity<Object> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getReview(@PathVariable(value="id") UUID reviewId) {
        return reviewService.getReview(reviewId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateReview(@PathVariable(value="id") UUID reviewId, @RequestBody @Valid ReviewRecordDto reviewRecordDto) {
        return reviewService.updateReview(reviewId, reviewRecordDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable(value="id") UUID reviewId) {
        return reviewService.deleteReview(reviewId);
    }
}
