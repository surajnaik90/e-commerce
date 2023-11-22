package com.productservice.productservice.Services;

import com.productservice.productservice.DTO.FakeStoreProductDTO;
import com.productservice.productservice.DTO.GenericProductDTO;

public interface ProductService {
    GenericProductDTO getProductById(Long id);
    void getAllProducts();
    void deleteProductById();
    void createProduct();
    void updateProductById();
}