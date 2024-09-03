package com.giovanna.reviewsmake.controller;

import com.giovanna.reviewsmake.dto.ProductRecordDto;
import com.giovanna.reviewsmake.model.ProductModel;
import com.giovanna.reviewsmake.repository.ProductRepository;
import com.giovanna.reviewsmake.repository.ReviewRepository;
import com.giovanna.reviewsmake.service.ProductService;
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
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDto productRecordDto) {
        return productService.saveProduct(productRecordDto);
    }

    @GetMapping("/products")
    public ResponseEntity<Object> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Object> getProduct(@PathVariable(value="id") UUID productId) {
        return productService.getProduct(productId);
    }

    @GetMapping("/products/{id}/reviews")
    public ResponseEntity<Object> getAllReviewsByProduct(@PathVariable(value = "id") UUID productId) {
        return productService.getAllReviewsByProduct(productId);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value="id") UUID productId, @RequestBody @Valid ProductRecordDto productRecordDto) {
        return productService.updateProduct(productId, productRecordDto);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable(value="id") UUID productId) {
        return productService.deleteProduct(productId);
    }
}