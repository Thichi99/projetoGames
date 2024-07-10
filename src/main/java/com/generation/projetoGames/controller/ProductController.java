package com.generation.projetoGames.controller;

import com.generation.projetoGames.model.Product;
import com.generation.projetoGames.repository.CategoryRepository;
import com.generation.projetoGames.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(productRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        return productRepository.findById(id)
                .map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/productName/{productName}")
    public ResponseEntity<List<Product>> getByProductName(@PathVariable String productName) {
        return ResponseEntity.ok(productRepository.findAllByProductNameContainingIgnoreCase(productName));
    }

    @PostMapping
    public ResponseEntity<Product> post(@Valid @RequestBody Product product) {
        if (categoryRepository.existsById(product.getCategory().getId())) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(productRepository.save(product));
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This category doesn't exists!", null);
    }

    @PutMapping
    public ResponseEntity<Product> put(@Valid @RequestBody Product product) {
        if (productRepository.existsById(product.getId())) {
            if (categoryRepository.existsById(product.getCategory().getId()))
                return ResponseEntity.status(HttpStatus.OK)
                        .body(productRepository.save(product));

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This category doesn't exists!", null);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Product> product = productRepository.findById(id);

        if (product.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        productRepository.deleteById(id);
    }

}
