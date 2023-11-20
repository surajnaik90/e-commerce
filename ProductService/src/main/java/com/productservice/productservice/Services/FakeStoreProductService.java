package com.productservice.productservice.Services;

import org.springframework.stereotype.Service;


//We name the service as "fakeStoreProductService" to help spring
//identify during constructor injection this implementation to choose
//based on the name we give it to the service.
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
