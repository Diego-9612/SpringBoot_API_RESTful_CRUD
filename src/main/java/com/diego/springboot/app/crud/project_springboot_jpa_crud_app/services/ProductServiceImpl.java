package com.diego.springboot.app.crud.project_springboot_jpa_crud_app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.diego.springboot.app.crud.project_springboot_jpa_crud_app.entities.Product;
import com.diego.springboot.app.crud.project_springboot_jpa_crud_app.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();

    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Transactional
    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    @Override
    public Optional<Product> delete(Product product) {
        Optional<Product> optionalProduct = productRepository.findById(product.getId());
        optionalProduct.ifPresent(prodBD -> {
            productRepository.delete(prodBD);
        });

        return optionalProduct;

    }

}
