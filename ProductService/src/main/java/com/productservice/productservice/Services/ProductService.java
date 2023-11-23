package com.productservice.productservice.Services;

import com.productservice.productservice.DTO.FakeStoreProductDTO;
import com.productservice.productservice.DTO.GenericProductDTO;
import java.util.List;

public interface ProductService {
    GenericProductDTO getProductById(Long id);
    List<GenericProductDTO> getAllProducts();
    GenericProductDTO deleteProductById(Long id);
    GenericProductDTO createProduct(GenericProductDTO genericProductDTO);
    void updateProductById();
}