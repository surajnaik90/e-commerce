package com.productservice.productservice.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel{
    private String title;
    private String description;
    private String image;
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Category category;

    //@OneToOne(optional = false, cascade = {CascadeType.REMOVE})
    @OneToOne(optional = false, cascade = {CascadeType.REMOVE})
    //@JoinColumn(nullable = false) //Just for the business case it is OneToOne
    private Price price;
}