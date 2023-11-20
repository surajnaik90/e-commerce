package com.productservice.productservice.Controller;

import com.productservice.productservice.Services.FakeStoreProductService;
import com.productservice.productservice.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    //Constructor injection
    @Autowired
    ProductController(@Qualifier("fakeStoreProductService") ProductService productService){
        this.productService = productService;
    }
    @GetMapping("/{id}")
    public String getProductById(@PathVariable("id") Long id){
        return productService.getProductById(id);
    }

    public void getAllProducts() {

    }


    public void updateProductById()
    {

    }
    @DeleteMapping("/{id}")
    public void deleteProductById(){

    }
    public void createProduct(){

    }
}