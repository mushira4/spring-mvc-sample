package br.com.spring.mvc.basics.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/home")
	public String index(){
		System.err.println("Chegou");
		return "hello-world";
	}
	
}
