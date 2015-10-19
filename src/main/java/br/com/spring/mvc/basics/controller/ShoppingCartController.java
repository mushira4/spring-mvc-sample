package br.com.spring.mvc.basics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView add(Integer productId, 
	/** Indicates that a method parameter should be bound to a web request parameter **/
	@RequestParam BookType bookType) {
		Product product = productDAO.find(productId);
		ShoppingItem item = new ShoppingItem(product, bookType);
		shoppingCart.add(item);

		ModelAndView modelAndView = new ModelAndView("redirect:/shopping");
		modelAndView.addObject("shoppingCart", shoppingCart);
		return modelAndView;
	}

	@RequestMapping(value = "remove", method = RequestMethod.POST)
	public String remove(Long productId, BookType bookType) {
		return "";
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView items() {
		ModelAndView modelAndView = new ModelAndView("shoppingCart/items");
		modelAndView.addObject("shoppingCart", shoppingCart);
		return modelAndView;
	}
}
