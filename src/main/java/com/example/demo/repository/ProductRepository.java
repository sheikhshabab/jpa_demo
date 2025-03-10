package com.example.demo.repository;

import com.example.demo.models.Product;
import java.util.List;

public interface ProductRepository {

    List<Product> findAll();

    Product findById(Long id);

    void save(Product product);

    void deleteById(Long id);
}
