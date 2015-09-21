package br.com.spring.mvc.basics.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.spring.mvc.basics.controller.HomeController;

@EnableWebMvc //Enable functionalities
@ComponentScan(basePackageClasses=HomeController.class) //Inform which package have to be loaded
public class AppWebConfig {

	@Bean
	public InternalResourceViewResolver internalResourceViewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix( "/WEB-INF/views/" );
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
}
