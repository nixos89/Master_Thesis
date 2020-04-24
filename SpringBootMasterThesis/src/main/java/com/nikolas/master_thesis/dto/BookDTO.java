package com.nikolas.master_thesis.dto;

import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookDTO {

	private Long bookId;

	private String title;

	private double price;

	private int amount;

	private boolean isDeleted;

	private Set<AuthorDTO> authors;

	private Set<CategoryDTO> categories;

	public BookDTO(Long bookId, String title, double price, int amount, boolean isDeleted) {
		this.bookId = bookId;
		this.title = title;
		this.price = price;
		this.amount = amount;
		this.isDeleted = isDeleted;
	}

}
