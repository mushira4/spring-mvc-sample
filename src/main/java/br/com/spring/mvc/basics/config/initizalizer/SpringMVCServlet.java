package br.com.spring.mvc.basics.config.initizalizer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import br.com.spring.mvc.basics.config.AmazonConfiguration;
import br.com.spring.mvc.basics.config.AppWebConfig;
import br.com.spring.mvc.basics.config.JPAConfiguration;
import br.com.spring.mvc.basics.config.SecurityConfiguration;

/**
 * Main configurations of SpringMVC. <br/>
 * It acts as an servlet receiving all the requests made for the application
 * 
 * @author mushira4
 */
public class SpringMVCServlet extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected void customizeRegistration(Dynamic registration) {
		// Configure the strategy for receiving files via upload
		// The empty String defines that the webserver will decide the temp storage path
		registration.setMultipartConfig(new MultipartConfigElement(""));
		super.customizeRegistration(registration);
	}
	
	/**
	 * Specify @Configuration and/or @Component classes to be provided to the root application context.
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[]{SecurityConfiguration.class};
	}

	/**
	 * Specify @Configuration and/or @Component classes to be provided to the dispatcher servlet application context.
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		Class<?>[] configClasses = new Class<?>[] { 
				AppWebConfig.class, 
				JPAConfiguration.class,
				AmazonConfiguration.class};
		return configClasses;
	}

	/**
	 * Specify the servlet mapping(s) for the DispatcherServlet. <br/>
	 * For instance: "/home", "/app" or etc
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

}
