package com.nikolas.master_thesis.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthorListDTO {

	private List<AuthorDTO> authorsDTO;

	public AuthorListDTO() {
		this.authorsDTO = new ArrayList<AuthorDTO>();
	}

}
