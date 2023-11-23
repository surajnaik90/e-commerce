package com.productservice.productservice.Controller;

import com.productservice.productservice.DTO.ExceptionDTO;
import com.productservice.productservice.DTO.FakeStoreProductDTO;
import com.productservice.productservice.DTO.GenericProductDTO;
import com.productservice.productservice.Exceptions.ProductNotFoundException;
import com.productservice.productservice.Services.FakeStoreProductService;
import com.productservice.productservice.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    //Constructor injection
    //@Qualifier is used to explicitly specify what service implementation to choose
    @Autowired
    ProductController(@Qualifier("fakeStoreProductService") ProductService productService){
        this.productService = productService;
    }

    //Path variable is used to parse the URL for a parameter
    //in this case it is 'id' and pass it to the method.
    @GetMapping("/{id}")
    public GenericProductDTO getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
        return productService.getProductById(id);
    }

    @GetMapping
    public List<GenericProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }


    @PutMapping
    public void updateProductById()
    {

    }
    @DeleteMapping("/{id}")
    public GenericProductDTO deleteProductById(Long id){
        return productService.deleteProductById(id);
    }

    @PostMapping
    public GenericProductDTO createProduct(@RequestBody GenericProductDTO genericProductDTO){
        return productService.createProduct(genericProductDTO);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    private ExceptionDTO handleProductNotFoundException(ProductNotFoundException ex) {

        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setMessage(ex.getMessage());
        exceptionDTO.setHttpStatus(HttpStatus.NOT_FOUND);
        return exceptionDTO;
    }
}