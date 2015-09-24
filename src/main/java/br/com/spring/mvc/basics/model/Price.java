package br.com.spring.mvc.basics.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Price {

	@Column(scale=2)
	private BigDecimal value;
	private BookType bookType;

	public Price() {}
	
	public Price(BigDecimal value, BookType bookType) {
		this.value = value;
		this.bookType = bookType;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public BookType getBookType() {
		return bookType;
	}

	public void setBookType(BookType bookType) {
		this.bookType = bookType;
	}
	
}
