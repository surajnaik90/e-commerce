package com.productservice.productservice.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericProductDTO {
    private Long id;
    private String title;
    private int price;
    private String description;
    private String category;
    private String image;
}
