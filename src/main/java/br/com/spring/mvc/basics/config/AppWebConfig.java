package br.com.spring.mvc.basics.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.spring.mvc.basics.controller.HomeController;
import br.com.spring.mvc.basics.daos.ProductDAO;
import br.com.spring.mvc.basics.infrastructure.FileSaver;
import br.com.spring.mvc.basics.model.ShoppingCart;

/**
 * All Spring configuration is inside that class
 * 
 * @author mushira4
 *
 */
@EnableWebMvc  // Enable functionalities
@EnableCaching // Enable caching system into the application
@ComponentScan(
		basePackageClasses = { // Inform which package have to be loaded 
			HomeController.class,
			ProductDAO.class, 
			FileSaver.class,
			ShoppingCart.class
		}
) 
public class AppWebConfig  {

	/**
	 * Configure the resource's page folder.
	 */
	@Bean //Indicates that a method produces a bean to be managed by the Spring container.
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");

		// Set whether to make all Spring beans in the application context accessible as request attributes, through lazy checking once an attribute gets accessed.
		// This will make all such beans accessible in plain ${...} expressions in a JSP 2.0 page, as well as in JSTL's c:out value expressions.
		// If you uncomment this all beans will be accessible in the pages.
		// resolver.setExposeContextBeansAsAttributes(true);
		
		//Allow the shopping cart session bean to be accessible into the page.
		resolver.setExposedContextBeanNames("shoppingCart"); 
		
		return resolver;
	}

	/**
	 * Configure the message's source folder. 
	 */
	@Bean(name="messageSource") //The name of the method need to be "messageSource", or the annotation should configure that name 
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource bundle = new ReloadableResourceBundleMessageSource();
		bundle.setBasename("/WEB-INF/messages");
		bundle.setDefaultEncoding("UTF-8");
		
		//Development mode cache, should be higher when in production
		bundle.setCacheSeconds(1); 

		return bundle;
	}
	
	/**
	 * Configure date default converter. 
	 */
	@Bean(name="mvcConversionService") //The name of the method need to be "mvcConversionService", or the annotation should configure that name 
	public FormattingConversionService mvcConversionService(){
		FormattingConversionService conversionService = new DefaultFormattingConversionService(true);
		DateFormatterRegistrar registrar = new DateFormatterRegistrar();
		registrar.setFormatter(new DateFormatter("yyyy-MM-dd"));
		registrar.registerFormatters(conversionService);
		return conversionService;
	}
	
	/**
	 * Returns the default implementation of the multipartResolver.
	 * MultipartResolver is responsible for handling the receiving uploaded files. 
	 */
	@Bean
	public MultipartResolver multipartResolver(){
		return new StandardServletMultipartResolver();
	}

	/**
	 * Register the Implementation to the Spring DI. 
	 */
	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

	//This solution is a very simple one
	//If you need something more robustcheck out: Ehcahe or GemFire
	//The Java JSR for cache is JSR-107.
	/**
	 * Configure the cachemanager. <br/>
	 * In this point you shall instatiate your cache implementation.
	 */
	@Bean
	public CacheManager cacheManager(){
		return new ConcurrentMapCacheManager();
	}
	
}