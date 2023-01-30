package com.techtitans.ecommerce.controllers;

import com.techtitans.ecommerce.dto.ProductDTO;
import com.techtitans.ecommerce.services.implementations.ProductServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    ProductServiceImplementation productService;

    @GetMapping("/products")
    public List<ProductDTO> getproducts(){
        return productService.getAllProducts().stream().map(product -> new ProductDTO(product)).collect(Collectors.toList());
    }

    @GetMapping("/product/{id}")
    public ProductDTO getProductById(@PathVariable Long id){
        return new ProductDTO(productService.findProductById(id));
    }
}
