package br.com.spring.mvc.basics.daos;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.spring.mvc.basics.config.DataSourceConfigurationTest;
import br.com.spring.mvc.basics.config.JPAConfiguration;
import br.com.spring.mvc.basics.model.BookType;
import br.com.spring.mvc.basics.model.Price;
import br.com.spring.mvc.basics.model.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DataSourceConfigurationTest.class, ProductDAO.class, JPAConfiguration.class })
@ActiveProfiles("test")
public class ProductDAOTest {

	@Autowired
	private ProductDAO productDAO;

	@Transactional
	@Test
	public void shouldSumAllPricesOfEachBookPerType() {
		List<Product> printedBooks = new ArrayList<>();

		printedBooks.stream().forEach(productDAO::save);

		List<Product> ebooks = new ArrayList<>();

		ebooks.stream().forEach(productDAO::save);

		productDAO.sumPricesPerType(BookType.PRINTED);

	}

	@Transactional
	@Test
	public void shouldSumAllPricesOfEachBookPerType2() {
		List<Product> printedBooks = new ArrayList<>();
		Product product = new Product();
		List<Price> prices = new ArrayList<>();
		Price price = new Price();
		price.setBookType(BookType.PRINTED);
		price.setValue(new BigDecimal("30"));
		prices.add(price);
		product.setPrices(prices);
		printedBooks.add(product);
		printedBooks.stream().forEach(productDAO::save);

		List<Product> ebooks = new ArrayList<>();

		ebooks.stream().forEach(productDAO::save);

		BigDecimal value = productDAO.sumPricesPerType(BookType.PRINTED);
		assertEquals(new BigDecimal(30).setScale(2), value);

	}

}
