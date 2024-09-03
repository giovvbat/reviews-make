package com.giovanna.reviewsmake.service;

import com.giovanna.reviewsmake.dto.ProductRecordDto;
import com.giovanna.reviewsmake.model.ProductModel;
import com.giovanna.reviewsmake.repository.ProductRepository;
import com.giovanna.reviewsmake.repository.ReviewRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    public ResponseEntity<ProductModel> saveProduct(ProductRecordDto productRecordDto) {
        var productModel = new ProductModel();
        BeanUtils.copyProperties(productRecordDto, productModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productModel));
    }

    public ResponseEntity<Object> getAllProducts() {
        List<ProductModel> products = productRepository.findAll();

        if(products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No products found!");
        }

        return ResponseEntity.status(HttpStatus.OK).body(productRepository.findAll());
    }

    public ResponseEntity<Object> getProduct(UUID productId) {
        Optional<ProductModel> product = productRepository.findById(productId);

        if(product.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found!");
        }

        return ResponseEntity.status(HttpStatus.OK).body(product.get());
    }

    public ResponseEntity<Object> getAllReviewsByProduct(UUID productId) {
        Optional<ProductModel> product = productRepository.findById(productId);

        if(product.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found!");
        }

        return ResponseEntity.status(HttpStatus.OK).body(reviewRepository.findAllByReviewProduct(product.get()));
    }

    public ResponseEntity<Object> updateProduct(UUID productId, ProductRecordDto productRecordDto) {
        Optional<ProductModel> product = productRepository.findById(productId);

        if(product.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found!");
        }

        BeanUtils.copyProperties(productRecordDto, product.get());
        return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(product.get()));
    }

    public ResponseEntity<String> deleteProduct(UUID productId) {
        Optional<ProductModel> product = productRepository.findById(productId);

        if(product.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found!");
        }

        productRepository.deleteById(productId);
        return ResponseEntity.status(HttpStatus.OK).body("Product successfully deleted!");
    }
}