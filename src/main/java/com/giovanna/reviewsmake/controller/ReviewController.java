package com.giovanna.reviewsmake.controller;

import com.giovanna.reviewsmake.dto.review.ReviewRecordDto;
import com.giovanna.reviewsmake.model.ReviewModel;
import com.giovanna.reviewsmake.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewModel> saveReview(@RequestBody @Valid ReviewRecordDto reviewRecordDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.saveReview(reviewRecordDto));
    }

    @GetMapping
    public ResponseEntity<List<ReviewModel>> getAllReviews() {
        return ResponseEntity.status(HttpStatus.OK).body(reviewService.getAllReviews());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewModel> getReview(@PathVariable(value="id") UUID reviewId) {
        return ResponseEntity.status(HttpStatus.OK).body(reviewService.getReview(reviewId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewModel> updateReview(@PathVariable(value="id") UUID reviewId, @RequestBody @Valid ReviewRecordDto reviewRecordDto) {
        return ResponseEntity.status(HttpStatus.OK).body(reviewService.updateReview(reviewId, reviewRecordDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable(value="id") UUID reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.status(HttpStatus.OK).body("Review successfully deleted!");
    }
}
