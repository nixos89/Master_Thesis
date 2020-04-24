package com.nikolas.master_thesis.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveUpdateAuthorDTO {

	private Long authorId;
	private String firstName;
	private String lastName;
//	private Set<Long> bookIds;
	
}
