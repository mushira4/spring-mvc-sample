package br.com.spring.mvc.basics.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.spring.mvc.basics.daos.ProductDAO;
import br.com.spring.mvc.basics.model.BookType;
import br.com.spring.mvc.basics.model.Product;

@Controller
@Transactional
@RequestMapping("/products")
public class ProductsController {

	@Autowired
	private ProductDAO productDao;

	@RequestMapping("/form")
	public String form(Model model){
		model.addAttribute("types", BookType.values());
		return "products/form";
	}

	@RequestMapping(method=RequestMethod.POST)
	public String save(Product product, RedirectAttributes redirectAttributes){
		System.out.println("Registering the product");
		productDao.save(product);
		redirectAttributes.addFlashAttribute("success", "Product added succesfully.");
		return "redirect:products";
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String list(Model model){
		System.out.println("Retrieving products");
		model.addAttribute("products", productDao.list());
		return "products/list";
	}
	
// Both ways are valid, it is up to you to decide which is the better option	
//	@RequestMapping("/products/form")
//	public ModelAndView form(){
//		ModelAndView modelAndView = new ModelAndView("products/form");
//		modelAndView.addObject("types", BookType.values());
//		return modelAndView;
//	}

}
