package com.productservice.productservice.inheritancerelations.mappedsuperclass;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Mentor extends User {

    private String name;
    private String email;
    private double avgRating;
}
