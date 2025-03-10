package com.example.demo.repository;

import com.example.demo.models.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CustomProductRepository implements ProductRepository{
    private Map<Long, Product> products = new HashMap<>();
    private Long nextId = 1L;

    @Override
    public ArrayList<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public Product findById(Long id) {
        return products.get(id);
    }

    @Override
    public void save(Product product) {
        if (product.getId() == null) {
            product.setId(nextId++);
        }
        products.put(product.getId(), product);
    }

    @Override
    public void deleteById(Long id) {
        products.remove(id);
    }
}
