package com.productservice.productservice.Services;

import com.productservice.productservice.DTO.FakeStoreProductDTO;
import com.productservice.productservice.DTO.GenericProductDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


//We name the service as "fakeStoreProductService" to help spring
//identify during constructor injection this implementation to choose
//based on the name we give it to the service.
@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{
    private RestTemplateBuilder restTemplateBuilder;

    private String getProductURL = "https://fakestoreapi.com/products/{id}";
    private String getAllProductsURL = "https://fakestoreapi.com/products/";

    private String createProductsURL = "https://fakestoreapi.com/products/";

    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    private GenericProductDTO convertToGenericProduct(FakeStoreProductDTO fakeStoreProductDTO){

        GenericProductDTO genericProductDTO = new GenericProductDTO();

        genericProductDTO.setId(fakeStoreProductDTO.getId());
        genericProductDTO.setDescription(fakeStoreProductDTO.getDescription());
        genericProductDTO.setCategory(fakeStoreProductDTO.getCategory());
        genericProductDTO.setImage(fakeStoreProductDTO.getImage());
        genericProductDTO.setPrice(fakeStoreProductDTO.getPrice());
        genericProductDTO.setTitle(fakeStoreProductDTO.getTitle());

        return genericProductDTO;
    }
    @Override
    public GenericProductDTO getProductById(Long id) {

        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDTO> responseEntity =
                restTemplate.getForEntity(getProductURL, FakeStoreProductDTO.class, id);


        return convertToGenericProduct(responseEntity.getBody());

    }

    @Override
    public List<GenericProductDTO> getAllProducts() {

        List<GenericProductDTO> list = new ArrayList<>();

        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO[]> responseEntity =
                restTemplate.getForEntity(getAllProductsURL, FakeStoreProductDTO[].class);

        List<FakeStoreProductDTO> fakeStoreProductDtos = List.of(responseEntity.getBody());
        for(FakeStoreProductDTO fakeStoreProductDTO : fakeStoreProductDtos){
            list.add(convertToGenericProduct(fakeStoreProductDTO));
        }

        return list;
    }

    @Override
    public GenericProductDTO deleteProductById(Long id) {

        RestTemplate restTemplate = restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDTO.class);

        ResponseExtractor<ResponseEntity<FakeStoreProductDTO>> responseExtractor =
                restTemplate.responseEntityExtractor(FakeStoreProductDTO.class);

        ResponseEntity<FakeStoreProductDTO> responseEntity =
                restTemplate.execute(getProductURL, HttpMethod.DELETE, requestCallback, responseExtractor, id);

        return convertToGenericProduct(responseEntity.getBody());
    }

    @Override
    public GenericProductDTO createProduct(GenericProductDTO genericProductDTO) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDTO> responseEntity =
                restTemplate.postForEntity(createProductsURL, genericProductDTO, FakeStoreProductDTO.class);

        return convertToGenericProduct(responseEntity.getBody());
    }

    @Override
    public void updateProductById() {

    }
}
