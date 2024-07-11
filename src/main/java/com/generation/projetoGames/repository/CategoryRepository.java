package com.generation.projetoGames.repository;

import com.generation.projetoGames.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByProductCategoryContainingIgnoreCase(@Param("productCategory") String productCategory);
}
