package com.productservice.productservice.controllers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.productservice.productservice.Controller.ProductController;
import com.productservice.productservice.DTO.GenericProductDTO;
import com.productservice.productservice.Services.ProductService;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerMVCTest {
    @MockBean
    private ProductService productService;
    @Inject
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;
    @Test
    void testGetAllProductsReturnsEmptyList() throws Exception {
        when(productService.getAllProducts())
                .thenReturn(new ArrayList<>());

        mockMvc.perform(get("/products"))
                .andExpect(status().is(200))
                .andExpect(content().string("[]"));
    }

    @Test
    void testGetAllProductsReturnsValidList() throws Exception {

        List<GenericProductDTO> genericProductDTOList = new ArrayList<>();
        genericProductDTOList.add(new GenericProductDTO());
        genericProductDTOList.add(new GenericProductDTO());
        genericProductDTOList.add(new GenericProductDTO());

        when(productService.getAllProducts())
                .thenReturn(genericProductDTOList);

        mockMvc.perform(get("/products"))
                .andExpect(status().is(200))
                .andExpect(content().string(objectMapper.writeValueAsString(genericProductDTOList)));
    }

    @Test
    void createProductShouldCreateAValidNewProduct() {

        GenericProductDTO genericProductDTO = new GenericProductDTO();
        genericProductDTO.setTitle("mackbook");
        genericProductDTO.setPrice(200000);
        genericProductDTO.setDescription("fastest mac ever");
        genericProductDTO.setCategory("laptop");

        GenericProductDTO genericProductDTO1 = new GenericProductDTO();
        genericProductDTO1.setCategory(genericProductDTO.getCategory());
        genericProductDTO1.setTitle(genericProductDTO.getTitle());
        genericProductDTO1.setPrice(genericProductDTO.getPrice());
        genericProductDTO1.setDescription(genericProductDTO.getDescription());
        genericProductDTO1.setId(100L);

        when(productService.createProduct(genericProductDTO))
                .thenReturn(genericProductDTO1);

        mockMvc.perform(post("/products"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(genericProductDTO))
            ).andExpect(content());

    }
}