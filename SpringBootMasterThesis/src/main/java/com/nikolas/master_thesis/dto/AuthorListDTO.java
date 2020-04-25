package com.nikolas.master_thesis.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthorListDTO {
	
	@JsonProperty("authors")
	private List<AuthorDTO> authorsDTO;

	public AuthorListDTO() {
		this.authorsDTO = new ArrayList<AuthorDTO>();
	}

}
