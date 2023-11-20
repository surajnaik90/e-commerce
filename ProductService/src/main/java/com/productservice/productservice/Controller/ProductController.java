package com.productservice.productservice.Controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    @GetMapping("/{id}")
    public String getProductById(@PathVariable("id") Long id){
        return "Product fetched with id: " + id;
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