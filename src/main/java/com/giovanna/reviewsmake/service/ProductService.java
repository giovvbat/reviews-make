package com.giovanna.reviewsmake.service;

import com.giovanna.reviewsmake.dto.product.ProductRecordDto;
import com.giovanna.reviewsmake.infra.exception.product.NoProductsFoundException;
import com.giovanna.reviewsmake.infra.exception.product.ProductNotFoundException;
import com.giovanna.reviewsmake.model.ProductModel;
import com.giovanna.reviewsmake.model.ReviewModel;
import com.giovanna.reviewsmake.repository.ProductRepository;
import com.giovanna.reviewsmake.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    @Transactional
    public ProductModel saveProduct(ProductRecordDto productRecordDto) {
        var productModel = new ProductModel();
        BeanUtils.copyProperties(productRecordDto, productModel);
        return productRepository.save(productModel);
    }

    public List<ProductModel> getAllProducts() {
        List<ProductModel> products = productRepository.findAll();

        if(products.isEmpty()) {
            throw new NoProductsFoundException();
        }

        return productRepository.findAll();
    }

    public Page<ProductModel> getAllPaginatedProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public ProductModel getProduct(UUID productId) {
        return productRepository.findById(productId)
                .orElseThrow(ProductNotFoundException::new);
    }

    public List<ReviewModel> getAllReviewsByProduct(UUID productId) {
        ProductModel product = productRepository.findById(productId)
                .orElseThrow(ProductNotFoundException::new);

        return reviewRepository.findAllByReviewProduct(product);
    }

    @Transactional
    public ProductModel updateProduct(UUID productId, ProductRecordDto productRecordDto) {
        ProductModel product = productRepository.findById(productId)
                .orElseThrow(ProductNotFoundException::new);

        BeanUtils.copyProperties(productRecordDto, product);

        return productRepository.save(product);
    }

    @Transactional
    public void deleteProduct(UUID productId) {
        ProductModel product = productRepository.findById(productId)
                .orElseThrow(ProductNotFoundException::new);

        reviewRepository.deleteByReviewProduct(product);
        productRepository.deleteById(productId);
    }
}