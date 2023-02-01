package com.techtitans.ecommerce.services.implementations;

import com.techtitans.ecommerce.models.Product;
import com.techtitans.ecommerce.repositories.ProductRepository;
import com.techtitans.ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImplementation implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product findProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public void saveProduct(Product product){
        productRepository.save(product);
    }
}
