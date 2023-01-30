package com.techtitans.ecommerce.services;

import com.techtitans.ecommerce.models.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    Product findProductById(Long id);
}
