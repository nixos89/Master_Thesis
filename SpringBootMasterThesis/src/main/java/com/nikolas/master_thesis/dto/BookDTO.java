package com.nikolas.master_thesis.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class BookDTO {

	private Long bookId;

	private String title;

	private double price;

	private int amount;

	@JsonProperty("is_deleted")
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
