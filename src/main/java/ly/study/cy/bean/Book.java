package ly.study.cy.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class Book implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4336732518813287214L;

	private Long id;

	private String name;

	private BigDecimal price;

	private String isbn;

	private String title;

	public Book(String isbn, String title) {
		this.isbn = isbn;
		this.title = title;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Book{" + "isbn='" + isbn + '\'' + ", title='" + title + '\'' + '}';
	}
}
