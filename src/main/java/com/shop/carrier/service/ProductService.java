package com.shop.carrier.service;

import com.shop.carrier.data.entity.Product;
import com.shop.carrier.data.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProductInfoById(Long productId) {
        // productId를 사용하여 제품 정보를 조회
        return productRepository.findById(productId).orElse(null);
    }
}
