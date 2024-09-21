package com.giovanna.reviewsmake.repository;

import com.giovanna.reviewsmake.model.ProductModel;
import com.giovanna.reviewsmake.model.ReviewModel;
import com.giovanna.reviewsmake.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewModel, UUID> {
    List<ReviewModel> findAllByReviewProduct(ProductModel product);
    void deleteByReviewProduct(ProductModel product);
    void deleteByReviewUser(UserModel user);
}
