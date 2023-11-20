package com.productservice.productservice.Services;

public interface ProductService {
    String getProductById(Long id);
    void getAllProducts();
    void deleteProductById();
    void createProduct();
    void updateProductById();
}