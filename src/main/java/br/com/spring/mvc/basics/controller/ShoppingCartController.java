package br.com.spring.mvc.basics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.spring.mvc.basics.daos.ProductDAO;
import br.com.spring.mvc.basics.model.BookType;
import br.com.spring.mvc.basics.model.Product;
import br.com.spring.mvc.basics.model.ShoppingCart;
import br.com.spring.mvc.basics.model.ShoppingItem;

@Controller
@RequestMapping("/shopping")
public class ShoppingCartController {

	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private ShoppingCart shoppingCart;
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView add(Integer productId, BookType booktype){
		Product product = productDAO.find(productId);
		ShoppingItem item = new ShoppingItem(product, booktype);
		shoppingCart.add(item);
		return new ModelAndView("redirect:/produtos");
	}
	
	
	
}
