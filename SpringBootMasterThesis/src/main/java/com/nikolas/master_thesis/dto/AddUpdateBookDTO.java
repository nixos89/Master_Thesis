package com.nikolas.master_thesis.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
	private List<Long> categoryIds;
	@JsonProperty("authors")
	private List<Long> authorIds;
	
}
