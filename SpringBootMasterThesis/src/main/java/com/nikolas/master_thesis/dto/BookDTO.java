package com.nikolas.master_thesis.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDTO {

	private Long bookId;

	private String name;

	private double price;

	private int amount;

	private boolean isDeleted;

}
