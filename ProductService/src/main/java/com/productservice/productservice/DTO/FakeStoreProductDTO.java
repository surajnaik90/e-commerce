package com.productservice.productservice.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Setter
@Getter
public class FakeStoreProductDTO {
    private Long id;
    private String title;
    private int price;
    private String description;
    private String category;
    private String image;
}
