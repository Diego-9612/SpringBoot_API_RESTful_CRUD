package com.diego.springboot.app.crud.project_springboot_jpa_crud_app.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.diego.springboot.app.crud.project_springboot_jpa_crud_app.ProductValidation;
import com.diego.springboot.app.crud.project_springboot_jpa_crud_app.entities.Product;
import com.diego.springboot.app.crud.project_springboot_jpa_crud_app.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductValidation productValidation;

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
    public ResponseEntity<?> create(@Valid @RequestBody Product product, BindingResult result) {
        productValidation.validate(product, result);
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Product productNew = productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productNew);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Product product, BindingResult result,
            @PathVariable Long id) {
        productValidation.validate(product, result);
        if (result.hasFieldErrors()) {
            return validation(result);
        }

        Optional<Product> opcionalProduct = productService.update(id, product);
        if (opcionalProduct.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(opcionalProduct.orElseThrow());
        }
        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Product product = new Product();
        product.setId(id);
        Optional<Product> optionalProduct = productService.delete(id);
        if (optionalProduct.isPresent()) {
            return ResponseEntity.ok(optionalProduct.orElseThrow());
        }
        return ResponseEntity.notFound().build();

    }

    private ResponseEntity<?> validation(BindingResult result) {

        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo" + " " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);

    }

}
