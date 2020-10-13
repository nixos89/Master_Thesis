package com.nikolas.master_thesis.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Table(name = "book")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_generator")
	@SequenceGenerator(name="book_generator", sequenceName="book_seq", allocationSize = 1)
	@EqualsAndHashCode.Include
	private Long bookId;

	private String title;

	private double price;

	private int amount;

	private boolean isDeleted;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "author_book", joinColumns = { @JoinColumn(name = "book_id") }, inverseJoinColumns = {@JoinColumn(name = "author_id") })
	@OrderBy("authorId ASC")
	private Set<Author> authors;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "category_book", joinColumns = { @JoinColumn(name = "book_id") }, inverseJoinColumns = {@JoinColumn(name = "category_id") })
	@OrderBy("categoryId ASC")
	private Set<Category> categories;

	@OneToMany(mappedBy = "book", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@OrderBy("orderItemId ASC")
	private Set<OrderItem> orderItems;

	public Book(Long bookId, String title, double price, int amount, boolean isDeleted) {
		this.bookId = bookId;
		this.title = title;
		this.price = price;
		this.amount = amount;
		this.isDeleted = isDeleted;
	}
		

}
