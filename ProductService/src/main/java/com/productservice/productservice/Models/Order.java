package com.productservice.productservice.Models;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Entity(name = "orders") // As order is the reserved keyword in mysql
public class Order extends BaseModel{
    @ManyToMany
    //create mapping table with this name and also name the columns accordingly
    @JoinTable(name = "products_orders",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;
}