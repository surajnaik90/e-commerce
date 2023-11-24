package com.productservice.productservice.Services;

import com.productservice.productservice.DTO.FakeStoreProductDTO;
import com.productservice.productservice.DTO.GenericProductDTO;
import com.productservice.productservice.Exceptions.ProductNotFoundException;
import com.productservice.productservice.ThirdPartyClients.FakeStoreClient.FakeStoreAdapter;
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
    private FakeStoreAdapter fakeStoreAdapter;
    public FakeStoreProductService(FakeStoreAdapter fakeStoreAdapter){
        this.fakeStoreAdapter = fakeStoreAdapter;
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
    public GenericProductDTO getProductById(Long id) throws ProductNotFoundException {
        return convertToGenericProduct(fakeStoreAdapter.getProductById(id));
    }

    @Override
    public List<GenericProductDTO> getAllProducts() {

        List<GenericProductDTO> list = new ArrayList<>();

        for (FakeStoreProductDTO fakeStoreProductDTO : fakeStoreAdapter.getAllProducts()){
            GenericProductDTO genericProductDTO = convertToGenericProduct(fakeStoreProductDTO);
            list.add(genericProductDTO);
        }

        return list;
    }

    @Override
    public GenericProductDTO deleteProductById(Long id) {
        return convertToGenericProduct(fakeStoreAdapter.deleteProductById(id));
    }

    @Override
    public GenericProductDTO createProduct(GenericProductDTO genericProductDTO) {
        return convertToGenericProduct(fakeStoreAdapter.createProduct(genericProductDTO));
    }

    @Override
    public void updateProductById() {

    }
}
