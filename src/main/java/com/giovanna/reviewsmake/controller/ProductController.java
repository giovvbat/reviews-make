package com.giovanna.reviewsmake.controller;

import com.giovanna.reviewsmake.domain.dto.product.ProductRecordDto;
import com.giovanna.reviewsmake.domain.model.ProductModel;
import com.giovanna.reviewsmake.domain.model.ReviewModel;
import com.giovanna.reviewsmake.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("*")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDto productRecordDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.saveProduct(productRecordDto));
    }

    @GetMapping("/products")
    public ResponseEntity<Object> getAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts());
    }

    @GetMapping("/products-paginated")
    public ResponseEntity<Page<ProductModel>> getAllPaginatedProducts(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllPaginatedProducts(pageable));
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductModel> getProduct(@PathVariable(value="id") UUID productId) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProduct(productId));
    }

    @GetMapping("/products/{id}/reviews")
    public ResponseEntity<List<ReviewModel>> getAllReviewsByProduct(@PathVariable(value = "id") UUID productId) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllReviewsByProduct(productId));
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ProductModel> updateProduct(@PathVariable(value="id") UUID productId, @RequestBody @Valid ProductRecordDto productRecordDto) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.updateProduct(productId, productRecordDto));
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable(value="id") UUID productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.status(HttpStatus.OK).body("Product successfully deleted!");
    }
}