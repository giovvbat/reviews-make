package com.giovanna.projectsti.repository;

import com.giovanna.projectsti.model.ProductModel;
import com.giovanna.projectsti.model.ReviewModel;
import com.giovanna.projectsti.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewModel, UUID> {
    List<ReviewModel> findAllByReviewProduct(ProductModel product);
}
