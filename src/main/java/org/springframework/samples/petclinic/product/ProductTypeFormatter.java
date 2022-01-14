package org.springframework.samples.petclinic.product;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

@Component
public class ProductTypeFormatter implements Formatter<ProductType>{

	private final ProductService productService;

	@Autowired
	public ProductTypeFormatter(ProductService productService) {
		this.productService = productService;
	}

	@Override
	public String print(ProductType productType, Locale locale) {
		return productType.getName();
	}

	@Override
	public ProductType parse(String text, Locale locale) throws ParseException {
		ProductType productType = this.productService.getProductType(text);
			if (productType != null) {
				return productType;
			}
		throw new ParseException("type not found: " + text, 0);
	}
    
}
