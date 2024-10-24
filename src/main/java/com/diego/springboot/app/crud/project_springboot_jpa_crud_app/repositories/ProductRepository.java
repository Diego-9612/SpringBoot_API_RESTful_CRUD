package com.diego.springboot.app.crud.project_springboot_jpa_crud_app.repositories;

import org.springframework.data.repository.CrudRepository;

import com.diego.springboot.app.crud.project_springboot_jpa_crud_app.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

    boolean existsBySku(String sku);
}
