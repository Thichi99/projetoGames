package com.generation.projetoGames.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "tb_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "This field is required!")
    @Size(min = 1, max = 100, message = "This field requires a minimum of 1 character and a maximum of 100 characters!")
    private String productName;

    @NotBlank(message = "This field is required!")
    @Size(min = 1, max = 1000, message = "This field requires a minimum of 1 character and a maximum of 1000 characters!")
    private String description;

    @NotNull(message = "This field is required! If the product is free, place a value of 0,00")
    private double productPrice;

    @ManyToOne
    @JsonIgnoreProperties("product")
    private Category category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
