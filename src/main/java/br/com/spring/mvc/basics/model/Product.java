package br.com.spring.mvc.basics.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull	
	// @NotBlank
	private String title;
	
	@Lob
	@NotNull
	// @NotBlank
	private String description;
	
	@Min(value = 30)
	private int pages;
	
	// To convert calendar object to String/Database date
	// @DateTimeFormat(iso=ISO.DATE) // This annotation is not needed anymore due to the mvcConversionService declaration 
	private Calendar releaseDate;
	
	@ElementCollection
	private List<Price> prices = new ArrayList<>();
	
	private String summaryPath;

	public long getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public List<Price> getPrices() {
		return prices;
	}
	public void setPrices(List<Price> prices) {
		this.prices = prices;
	}
	public Calendar getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Calendar releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getSummaryPath() {
		return summaryPath;
	}
	public void setSummaryPath(String summaryPath) {
		this.summaryPath = summaryPath;
	}

}
