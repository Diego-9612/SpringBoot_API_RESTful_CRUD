package com.diego.springboot.app.crud.project_springboot_jpa_crud_app.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diego.springboot.app.crud.project_springboot_jpa_crud_app.entities.Product;
import com.diego.springboot.app.crud.project_springboot_jpa_crud_app.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> list() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id) {
        Optional<Product> optionalProduct = productService.findById(id);
        if (optionalProduct.isPresent()) {
            return ResponseEntity.ok(optionalProduct.orElseThrow());
        }
        return ResponseEntity.notFound().build();

    }

    @PostMapping
    public ResponseEntity<Product> create(@Valid @RequestBody Product product) {
        Product productNew = productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productNew);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id,@Valid @RequestBody Product product) {
        Optional<Product> opcionalProduct = productService.update(id, product);
        if (opcionalProduct.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(opcionalProduct.orElseThrow());
        }
        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id) {
        Product product = new Product();
        product.setId(id);
        Optional<Product> optionalProduct = productService.delete(id);
        if (optionalProduct.isPresent()) {
            return ResponseEntity.ok(optionalProduct.orElseThrow());
        }
        return ResponseEntity.notFound().build();

    }

}
