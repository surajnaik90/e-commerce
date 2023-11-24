package com.productservice.productservice.ThirdPartyClients.FakeStoreClient;

import com.productservice.productservice.DTO.FakeStoreProductDTO;
import com.productservice.productservice.DTO.GenericProductDTO;
import com.productservice.productservice.Exceptions.ProductNotFoundException;
import com.productservice.productservice.ThirdPartyClients.ThirdPartyInterface;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

//Implementing the Adapter Design Pattern

@Component
public class FakeStoreAdapter {

    @Value("${fakestore.api.url}")
    private String fakeStoreUrl;

    @Value("${fakestore.api.paths.products}")
    private String fakeStoreProductPaths;

    private RestTemplateBuilder restTemplateBuilder;
    private String getProductURL = "https://fakestoreapi.com/products/{id}";
    private String getAllProductsURL = "https://fakestoreapi.com/products/";
    private String createProductsURL = "https://fakestoreapi.com/products/";

    public FakeStoreAdapter(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
        this.getAllProductsURL = fakeStoreUrl + fakeStoreProductPaths;
        this.createProductsURL = fakeStoreUrl + fakeStoreProductPaths;
        this.getProductURL = fakeStoreUrl + fakeStoreProductPaths + "{id}";
    }

    public FakeStoreProductDTO getProductById(Long id) throws ProductNotFoundException {

        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDTO> responseEntity =
                restTemplate.getForEntity(getProductURL, FakeStoreProductDTO.class, id);

        FakeStoreProductDTO fakeStoreProductDTO = responseEntity.getBody();

        if(fakeStoreProductDTO == null) {
            throw new ProductNotFoundException("Product " + id + " not found");
        }

        return responseEntity.getBody();

    }


    public List<FakeStoreProductDTO> getAllProducts() {

        List<GenericProductDTO> list = new ArrayList<>();

        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO[]> responseEntity =
                restTemplate.getForEntity(getAllProductsURL, FakeStoreProductDTO[].class);

        List<FakeStoreProductDTO> fakeStoreProductDtos = List.of(responseEntity.getBody());

        return fakeStoreProductDtos;
    }


    public FakeStoreProductDTO deleteProductById(Long id) {

        RestTemplate restTemplate = restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDTO.class);

        ResponseExtractor<ResponseEntity<FakeStoreProductDTO>> responseExtractor =
                restTemplate.responseEntityExtractor(FakeStoreProductDTO.class);

        ResponseEntity<FakeStoreProductDTO> responseEntity =
                restTemplate.execute(getProductURL, HttpMethod.DELETE, requestCallback, responseExtractor, id);

        return responseEntity.getBody();
    }


    public FakeStoreProductDTO createProduct(GenericProductDTO genericProductDTO) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDTO> responseEntity =
                restTemplate.postForEntity(createProductsURL, genericProductDTO, FakeStoreProductDTO.class);

        return responseEntity.getBody();
    }


    public void updateProductById() {

    }
}
