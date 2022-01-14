package org.springframework.samples.petclinic.product;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.pet.Pet;
import org.springframework.samples.petclinic.pet.PetType;
import org.springframework.samples.petclinic.pet.exceptions.DuplicatedPetNameException;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {

	private final ProductService productService;
	
	@Autowired
	public ProductController(ProductService productService) {
		this.productService=productService;
	}
	
	@ModelAttribute("types")
	public Collection<ProductType> populatePetTypes() {
		ProductType a = productService.getProductType("Accessories");
		ProductType b = productService.getProductType("Food");
		Collection<ProductType> l = new ArrayList<>();
		l.add(a); l.add(b);
		return l;
	}
	
	
	@GetMapping(value="/product/create")
	public String initCreationForm(Map<String,Object> model) {
		
		Product product = new Product();
		model.put("product", product);
		return "products/createOrUpdateProductForm";
		
	}
	
	@PostMapping(value = "/product/create")
	public String processCreationForm(@Valid Product product, BindingResult result, ModelMap model) {		
		if (result.hasErrors()) {
			model.put("product", product);
			return "products/createOrUpdateProductForm";
		}
		else {
			productService.save(product);
			return "welcome";
	}

	}
}
