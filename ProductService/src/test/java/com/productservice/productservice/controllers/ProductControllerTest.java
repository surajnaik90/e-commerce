package com.productservice.productservice.controllers;

import com.productservice.productservice.Controller.ProductController;
import com.productservice.productservice.Exceptions.ProductNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ProductControllerTest {

    @Autowired
    private ProductController productController;

    ProductControllerTest(ProductController productController) {
        this.productController = productController
    }
    @Test
    @DisplayName("Testing 1+1 is 2")
    void testOnePlusOneisTwoOrNot(){
        assert(2 == 1+1);
    }

    @Test
    void testGetProductByIdNegativeTestCase()  throws ProductNotFoundException {
        assertThrows(ProductNotFoundException.class, () -> productController.getProductById(10000L));
    }
}