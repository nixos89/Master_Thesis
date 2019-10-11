package com.nikolas.master_thesis.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO {

	private Long categoryId;

	private String name;

	private boolean isDeleted;
}
