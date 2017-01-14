package br.com.spring.mvc.basics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;


/**
 * All Spring configuration is inside that class
 * 
 * @author mushira4
 *
 */
@EnableCaching // Enable caching system into the application
@SpringBootApplication
public class AppWebConfig  {

	public static void main(String args[]){
		SpringApplication.run(AppWebConfig.class, args);
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
