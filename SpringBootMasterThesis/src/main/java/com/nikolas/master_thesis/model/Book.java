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
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "book")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookId;

	private String title;

	private double price;

	private int amount;

	private boolean isDeleted;

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "author_book", joinColumns = { @JoinColumn(name = "book_id") }, inverseJoinColumns = {@JoinColumn(name = "author_id") })
	private Set<Author> authors;

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "category_book", joinColumns = { @JoinColumn(name = "book_id") }, inverseJoinColumns = {@JoinColumn(name = "category_id") })
	private Set<Category> categories;

	@OneToMany(mappedBy = "book", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Set<OrderItem> orderItems;

	public Book(Long bookId, String title, double price, int amount, boolean isDeleted) {
		this.bookId = bookId;
		this.title = title;
		this.price = price;
		this.amount = amount;
		this.isDeleted = isDeleted;
	}

}
