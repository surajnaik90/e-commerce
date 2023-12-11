package com.productservice.productservice.controllers;

import com.productservice.productservice.Controller.ProductController;
import com.productservice.productservice.DTO.GenericProductDTO;
import com.productservice.productservice.Exceptions.ProductNotFoundException;
import com.productservice.productservice.Services.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockBean
    private ProductService productService;

    ProductControllerTest(ProductController productController) {
        this.productController = productController;
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
    @Test
    void testGetProductByIdMocking() throws ProductNotFoundException{
        when(productService.getProductById(100L)).thenReturn(null);

        assertNull(productController.getProductById(100L));
    }

    @Test
    void testGetProductByIdMocking2() throws ProductNotFoundException{
        GenericProductDTO genericProductDTO = new GenericProductDTO();
        when(productService.getProductById(100L)).thenReturn(genericProductDTO);
        when(productService.getProductById(any(Long.class))).thenReturn(genericProductDTO);

        GenericProductDTO genericProductDTO1 = productController.getProductById(100L);

        assertEquals(genericProductDTO, genericProductDTO1);
    }

    @Test
    void testGetProductByIDMockingException() throws ProductNotFoundException{
        when(productService.getProductById(1L))
                .thenThrow(ProductNotFoundException.class);

        assertThrows(ProductNotFoundException.class, () -> productController.getProductById(1L));
    }

    @Test
    void testGetProductByIdForCustomLogic() throws ProductNotFoundException {

        GenericProductDTO genericProductDTO = new GenericProductDTO();
        when(productService.getProductById(10L))
                .thenReturn(genericProductDTO);

        assertEquals(genericProductDTO, productController.getProductById(10L));
    }
}