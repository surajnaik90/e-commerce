package com.productservice.productservice.Services;

import org.springframework.stereotype.Service;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{
    @Override
    public String getProductById(Long id) {
        return "Product fetched with id: " + id;
    }

    @Override
    public void getAllProducts() {

    }

    @Override
    public void deleteProductById() {

    }

    @Override
    public void createProduct() {

    }

    @Override
    public void updateProductById() {

    }
}
