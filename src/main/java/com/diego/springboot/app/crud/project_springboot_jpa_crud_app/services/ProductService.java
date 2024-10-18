package com.diego.springboot.app.crud.project_springboot_jpa_crud_app.services;

import java.util.List;
import java.util.Optional;

import com.diego.springboot.app.crud.project_springboot_jpa_crud_app.entities.Product;

public interface ProductService {

    List<Product> findAll();

    Optional<Product> findById(Long id);

    Product save(Product product);

    Optional<Product> delete(Product product);

}
