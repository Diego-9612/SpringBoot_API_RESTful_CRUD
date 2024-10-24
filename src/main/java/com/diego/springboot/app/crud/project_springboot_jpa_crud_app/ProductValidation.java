package com.diego.springboot.app.crud.project_springboot_jpa_crud_app;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.diego.springboot.app.crud.project_springboot_jpa_crud_app.entities.Product;

@Component
public class ProductValidation implements Validator {

    @Override
    public boolean supports(@SuppressWarnings("null") Class<?> clazz) {
        return Product.class.isAssignableFrom(clazz);
    }

    @SuppressWarnings("null")
    @Override
    public void validate(Object target, Errors errors) {
        Product product = (Product) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", null, "Este campo es requerido");

        if (product.getDescription() == null || product.getDescription().isBlank() ){
            errors.rejectValue("Description", null, "este campo es requerido por favor");
        }

        if (product.getPrice() == null){
            errors.rejectValue("Price", null, "este campo no debe ser nulo");
        } else if (product.getPrice() < 500){
            errors.rejectValue("Price", null, "este campo debe ser mayor a 500");
        }
    }
}
