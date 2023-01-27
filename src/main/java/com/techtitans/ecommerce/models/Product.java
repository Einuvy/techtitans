package com.techtitans.ecommerce.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.AUTO;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    private String name;

    private Double price;

    private String code;

    private String description;

    private Boolean deleted;

    private Integer stock;

    private String brand;

    @ElementCollection
    private Set<String> categories = new HashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<CartProduct> cartProducts;
    public Product() {
    }

    public Product(String name,
                   Double price,
                   String code,
                   String description,
                   Boolean deleted,
                   Integer stock,
                   String brand,
                   Set<String> categories) {
        this.name = name;
        this.price = price;
        this.code = code;
        this.description = description;
        this.deleted = deleted;
        this.stock = stock;
        this.brand = brand;
        this.categories = categories;
    }

    public void addCartProduct(CartProduct cartProduct){
        cartProduct.setProduct(this);
        cartProducts.add(cartProduct);
    }
    public void addCategory(String string){
        categories.add(string);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
