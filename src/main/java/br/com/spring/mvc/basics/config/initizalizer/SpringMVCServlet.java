package br.com.spring.mvc.basics.config.initizalizer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.context.request.RequestContextListener;
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

	/**
	 * Due to datasource profiles settings added in the classes JPAConfiguration
	 * and DataSourceConfigurationTest, is necesseray to choose the profile that
	 * shall run when the app startup.
	 */
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);
		servletContext.addListener(RequestContextListener.class);
		servletContext.setInitParameter("spring.profiles.active", "dev");
	}

	@Override
	protected void customizeRegistration(Dynamic registration) {
		// Configure the strategy for receiving files via upload
		// The empty String defines that the webserver will decide the temp
		// storage path
		registration.setMultipartConfig(new MultipartConfigElement(""));
		super.customizeRegistration(registration);
	}

	/**
	 * Specify @Configuration and/or @Component classes to be provided to the
	 * root application context.
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		// All the classes related to the security filter shall start at the
		// beginning of the app startup
		return new Class[] { AppWebConfig.class, JPAConfiguration.class, SecurityConfiguration.class,
				AmazonConfiguration.class };
	}

	/**
	 * Specify @Configuration and/or @Component classes to be provided to the
	 * dispatcher servlet application context.
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		Class<?>[] configClasses = new Class<?>[] {};
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
