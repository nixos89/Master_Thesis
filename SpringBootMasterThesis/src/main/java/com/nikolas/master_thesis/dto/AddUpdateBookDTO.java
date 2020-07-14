package com.nikolas.master_thesis.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddUpdateBookDTO {

	private String title;
	private double price;
	private int amount;
	@JsonProperty("isDeleted")
	private boolean deleted;
	@JsonProperty("categories")
	private Set<Long> categoryIds;
	@JsonProperty("authors")
	private Set<Long> authorIds;
	
}
