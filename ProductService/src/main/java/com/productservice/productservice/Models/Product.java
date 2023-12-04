package com.productservice.productservice.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseModel{
    private String title;
    private String description;
    private String image;
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Category category;

    //@OneToOne(optional = false, cascade = {CascadeType.REMOVE})
    @OneToOne(optional = false, cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    //@JoinColumn(nullable = false) //Just for the business case it is OneToOne
    private Price price;
}