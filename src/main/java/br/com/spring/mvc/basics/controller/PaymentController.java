package br.com.spring.mvc.basics.controller;

import java.math.BigDecimal;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.spring.mvc.basics.model.PaymentData;
import br.com.spring.mvc.basics.model.ShoppingCart;

@Controller
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private ShoppingCart shoppingCart;

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * When is used an Callable class as the method return, the execution will be asynchronous.
	 * It means that the thread won't be blocked due to the third party system slow response time. 
	 */
	@RequestMapping(value = "checkout", method = RequestMethod.POST)
	public Callable<String> checkout() {
		return () -> {
			BigDecimal total = shoppingCart.getTotal();

			String uriToPay = "http://book-payment.herokuapp.com/payment";

			try {
				restTemplate.postForObject(uriToPay, new PaymentData(total), String.class);
				return "redirect:/success";
			} catch (HttpClientErrorException exception) {
				return "redirect:/payment/error";
			}
		};
	}

}
