package br.com.spring.mvc.basics.controller;

import javax.servlet.Filter;
import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.amazonaws.services.s3.AmazonS3Client;

import br.com.spring.mvc.basics.config.AppWebConfig;
import br.com.spring.mvc.basics.config.DataSourceConfigurationTest;
import br.com.spring.mvc.basics.config.JPAConfiguration;
import br.com.spring.mvc.basics.config.SecurityConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { 
		AppWebConfig.class, 
		JPAConfiguration.class, 
		AmazonS3Client.class,
		SecurityConfiguration.class,
		DataSourceConfigurationTest.class })
@ActiveProfiles("test")
public class ProductsControllerTest {

	@Autowired
	private Filter springSecurityFilterChain;

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = 
				MockMvcBuilders
					.webAppContextSetup(this.wac)
					.addFilters(springSecurityFilterChain)
					.build();
	}

	@Test
	@Transactional
	public void shouldListAllBooksInTheHomeAndProvideAttributeProducts() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/products"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("products"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/products/list.jsp"));
	}
	
	@Test
	@Transactional
	public void shouldListAllBooksInTheHome() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/products"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/products/list.jsp"));

	}
	
	@Test
	public void onlyAdminShouldAccessProductsForm() throws Exception {
		RequestPostProcessor processor = 
				SecurityMockMvcRequestPostProcessors
					.user( "comprador@gmail.com" )
					.password( "123456" )
					.roles("COMPRADOR");
		
		this.mockMvc.perform(MockMvcRequestBuilders.get("/products/form").with(processor))
				.andExpect(MockMvcResultMatchers.status().is(403));
		
	}

}
