package br.com.spring.mvc.basics.daos;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.spring.mvc.basics.model.BookType;
import br.com.spring.mvc.basics.model.Product;

@Repository
public class ProductDAO {

	@PersistenceContext
	private EntityManager manager;

	public void save(Product product) {
		manager.persist(product);
	}

	public List<Product> list() {
		return manager.createQuery("select distinct(p) from Product p join fetch p.prices", Product.class)
				.getResultList();
	}

	public Product find(long id) {
		return manager.find(Product.class, id);
	}

	public BigDecimal sumPricesPerType(BookType bookType) {
		TypedQuery<BigDecimal> query = manager.createQuery(
				"select sum(price.value) from Product p "
				+ "join p.prices price where price.bookType=:bookType",
				BigDecimal.class);
		
		query.setParameter("bookType", bookType);
		return query.getSingleResult();
	}

}
