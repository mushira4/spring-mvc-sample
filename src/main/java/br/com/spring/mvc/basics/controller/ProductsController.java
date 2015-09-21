package br.com.spring.mvc.basics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.spring.mvc.basics.daos.ProductDAO;
import br.com.spring.mvc.basics.model.Product;

@Controller
public class ProductsController {

	@Autowired
	private ProductDAO productDao;
	
	@RequestMapping("/products")
	public String save(Product product){
		System.out.println("Registering the product");
		productDao.save(product);
		return "products/ok";
	}
	
	@RequestMapping("/products/form")
	public String form(){
		
		return "products/form";
	}
	
}
