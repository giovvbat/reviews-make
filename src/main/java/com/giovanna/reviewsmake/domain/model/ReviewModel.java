package com.giovanna.reviewsmake.domain.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "tb_review")
public class ReviewModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "review_id")
    private UUID reviewId;

    @JoinColumn(name = "user_name")
    @ManyToOne
    private UserModel reviewUser;

    @JoinColumn(name = "product_id")
    @ManyToOne
    private ProductModel reviewProduct;

    @Column(name = "review_comment")
    private String comment;

    public UUID getReviewId() {
        return reviewId;
    }

    public void setReviewId(UUID reviewId) {
        this.reviewId = reviewId;
    }

    public UserModel getReviewUser() {
        return reviewUser;
    }

    public void setReviewUser(UserModel reviewUser) {
        this.reviewUser = reviewUser;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public ProductModel getReviewProduct() {
        return reviewProduct;
    }

    public void setReviewProduct(ProductModel reviewProduct) {
        this.reviewProduct = reviewProduct;
    }
}
