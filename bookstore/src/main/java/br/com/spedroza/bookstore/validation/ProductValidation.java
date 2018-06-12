package br.com.spedroza.bookstore.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.spedroza.bookstore.model.Product;

public class ProductValidation implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// check if the target classe is a Product class
		System.out.println("Inside ProductValidation.supports...");
		return Product.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		System.out.println("Inside ProductValidation.validate...");
		System.out.println("Checking mandatory fields...");
		
		ValidationUtils.rejectIfEmpty(errors, "title", "field.required");
        ValidationUtils.rejectIfEmpty(errors, "description", "field.required");

        Product product = (Product) target;
        if(product.getPages() <= 0) {
            errors.rejectValue("pages", "field.required");
        }
	}
}
