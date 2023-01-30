package com.techtitans.ecommerce.dto;

import com.techtitans.ecommerce.models.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductDTO {

    private String name;

    private Double price;

    private String code;

    private String description;

    private Integer stock;

    private String brand;

    private List<String> categories;

    public ProductDTO(Product product) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.code = product.getCode();
        this.description = product.getDescription();
        this.stock = product.getStock();
        this.brand = product.getBrand();
        this.categories = product.getCategories().stream().collect(Collectors.toList());
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public Integer getStock() {
        return stock;
    }

    public String getBrand() {
        return brand;
    }

    public List<String> getCategories() {
        return categories;
    }
}
