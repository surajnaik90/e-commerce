package com.productservice.productservice.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import java.util.List;


@Getter
@Setter
@Entity
public class Category extends BaseModel{
    private String name;
    //category below is the name of the attribute in the Product class
    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
