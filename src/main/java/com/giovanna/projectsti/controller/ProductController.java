package com.giovanna.projectsti.controller;

import com.giovanna.projectsti.dto.ProductRecordDto;
import com.giovanna.projectsti.model.ProductModel;
import com.giovanna.projectsti.repository.ProductRepository;
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
    private ProductRepository productRepository;

    @PostMapping("/products")
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDto productRecordDto) {
        var productModel = new ProductModel();
        BeanUtils.copyProperties(productRecordDto, productModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productModel));
    }

    @GetMapping("/products")
    public ResponseEntity<Object> getAllProducts() {
        List<ProductModel> products = productRepository.findAll();

        if(products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No products found!");
        }

        return ResponseEntity.status(HttpStatus.OK).body(productRepository.findAll());
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Object> getProduct(@PathVariable(value="id") UUID productId) {
        Optional<ProductModel> product = productRepository.findById(productId);

        if(product.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found!");
        }

        return ResponseEntity.status(HttpStatus.OK).body(product.get());
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value="id") UUID productId, @RequestBody @Valid ProductRecordDto productRecordDto) {
        Optional<ProductModel> product = productRepository.findById(productId);

        if(product.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found!");
        }

        BeanUtils.copyProperties(productRecordDto, product.get());
        return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(product.get()));
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable(value="id") UUID productId) {
        Optional<ProductModel> product = productRepository.findById(productId);

        if(product.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found!");
        }

        productRepository.deleteById(productId);
        return ResponseEntity.status(HttpStatus.OK).body("Product successfully deleted!");
    }
}
