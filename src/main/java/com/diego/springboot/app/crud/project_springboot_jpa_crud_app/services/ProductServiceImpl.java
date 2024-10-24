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

    @Override
    @Transactional
    public Optional<Product> update(Long id, Product product) {

        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product prodBD = optionalProduct.orElseThrow();
            prodBD.setName(product.getName());
            prodBD.setDescription(product.getDescription());
            prodBD.setPrice(product.getPrice());
            ;
            return Optional.of(productRepository.save(prodBD));
        }

        return optionalProduct;

    }

    @Transactional
    @Override
    public Optional<Product> delete(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        optionalProduct.ifPresent(prodBD -> {
            productRepository.delete(prodBD);
        });

        return optionalProduct;

    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsBySku(String sku) {
        return productRepository.existsBySku(sku);
    }

}
