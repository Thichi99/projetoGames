package com.generation.projetoGames.controller;

import com.generation.projetoGames.model.Category;
import com.generation.projetoGames.repository.CategoryRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public ResponseEntity<List<Category>> getAll() {
        return ResponseEntity.ok(categoryRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable Long id) {
        return categoryRepository.findById(id)
                .map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/productCategory/{productCategory}")
    public ResponseEntity<List<Category>> getByTitle(@PathVariable String productCategory) {
        return ResponseEntity.ok(categoryRepository.findAllByDescriptionContainingIgnoreCase(productCategory));
    }

    @PostMapping
    public ResponseEntity<Category> post(@Valid @RequestBody Category category) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoryRepository.save(category));
    }

    @PutMapping
    public ResponseEntity<Category> put(@Valid @RequestBody Category category) {
        return categoryRepository.findById(category.getId())
                .map(resposta -> ResponseEntity.status(HttpStatus.CREATED)
                        .body(categoryRepository.save(category)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Category> category = categoryRepository.findById(id);

        if (category.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        categoryRepository.deleteById(id);
    }


}
