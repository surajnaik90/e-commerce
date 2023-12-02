package com.productservice.productservice.inheritancerelations.tableperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Mentor extends User {

    private String name;
    private String email;
    private double avgRating;
}
