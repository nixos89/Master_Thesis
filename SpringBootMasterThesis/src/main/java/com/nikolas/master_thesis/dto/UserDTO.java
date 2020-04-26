package com.nikolas.master_thesis.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.nikolas.master_thesis.model.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UserDTO {

	private Long userId;
	private String username;	
	
	public UserDTO(User user) {
		this.userId = user.getUserId();
		this.username = user.getUsername();
	}
	
}
