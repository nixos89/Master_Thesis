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
public class AddUpdateBookDTO {

	private String title;
	private double price;
	private int amount;
	@JsonProperty("is_deleted")
	private boolean deleted;
	@JsonProperty("categories")
	private Set<Long> categoryIds;
	@JsonProperty("authors")
	private Set<Long> authorIds;
	
}
