package br.com.spring.mvc.basics.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.spring.mvc.basics.daos.ProductDAO;
import br.com.spring.mvc.basics.infrastructure.FileSaver;
import br.com.spring.mvc.basics.model.BookType;
import br.com.spring.mvc.basics.model.Product;

@Controller
@Transactional
@RequestMapping("/products")
public class ProductsController {

	@Autowired
	@Qualifier("amazonS3FileSaver")
	private FileSaver fileSaver;
	
	@Autowired
	private ProductDAO productDao;

	// This bit of code was used to bind validators to the beans.
	// It is commente due to the use of bean validation's JSR.
	// @InitBinder
	// protected void initBinder(WebDataBinder binder) {
	// binder.setValidator(new ProductValidator());
	// }

	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public ModelAndView show(@PathVariable("id") Long id){
		Product product = productDao.find(id);
		ModelAndView model = new ModelAndView("products/show"); //The page that will be returned
		model.addObject("product", product);
		return model;
	}
	
	
	@RequestMapping("/form")
	public String form(Product product, Model model) {
		model.addAttribute("types", BookType.values());
		return "products/form";
	}

	@RequestMapping(method = RequestMethod.POST,
	/** use this parameter to customize the parameter of mvcUrl taglib function */
	name = "saveProduct")
	public String save(MultipartFile summary, @Valid Product product, Model model, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		System.out.println("Registering the product");
		if (bindingResult.hasErrors()) {
			return form(product, model);
		}

		String webPath = fileSaver.write("uploaded-images", summary);
		product.setSummaryPath(webPath);
		
		productDao.save(product);
		redirectAttributes.addFlashAttribute("success", "Product added succesfully.");
		
		
		// Using the "always redirect after post" pattern
		return "redirect:products";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {
		System.out.println("Retrieving products");
		model.addAttribute("products", productDao.list());
		return "products/list";
	}

	// Both ways are valid, it is up to you to decide which is the better option
	// @RequestMapping("/products/form")
	// public ModelAndView form(){
	// ModelAndView modelAndView = new ModelAndView("products/form");
	// modelAndView.addObject("types", BookType.values());
	// return modelAndView;
	// }

	@RequestMapping(value="checkout",method=RequestMethod.POST)
	public String checkout(){
		return "";
	}
	
	
}
