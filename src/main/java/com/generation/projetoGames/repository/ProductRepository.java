package com.generation.projetoGames.repository;

import com.generation.projetoGames.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByProductNameContainingIgnoreCase(@Param("productname") String productName);
}
