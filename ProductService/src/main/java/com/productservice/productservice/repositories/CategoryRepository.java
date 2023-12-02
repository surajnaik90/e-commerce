package com.productservice.productservice.repositories;

import com.productservice.productservice.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    @Override
    <S extends Category> S save(S entity);
}
