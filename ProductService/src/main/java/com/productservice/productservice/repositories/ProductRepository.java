package com.productservice.productservice.repositories;

import com.productservice.productservice.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    @Override
    <S extends Product> S save(S entity);
    @Override
    List<Product> findAll(); //Get all products from the table

    List<Product> findAllByTitle(String title);
    List<Product> findAllByTitleAndDescription(String title, String description);

    List<Product> findAllByPriceGreaterThan(Integer x);

    //Possible to overrisde the query behaviour by using Query annotation
    @Query(value = "select * from product", nativeQuery = true)
    List<Product> findAllByPrice_ValueBetween(Integer x, Integer y);
}