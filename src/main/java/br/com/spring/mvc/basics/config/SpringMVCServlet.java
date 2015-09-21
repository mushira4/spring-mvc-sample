package br.com.spring.mvc.basics.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Main configurations of SpringMVC. <br/>
 * It acts as an servlet receiving all the requests made for the application
 * @author mushira4
 */
public class SpringMVCServlet extends
		AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}

	/**
	 * Configuration that shows which classes should be read and loaded.
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[]{ AppWebConfig.class };
	}

	/**
	 * Specify the servlet mapping(s) for the DispatcherServlet. <br/>
	 * For instance: "/home", "/app" or etc
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[]{"/"};
	}

}
