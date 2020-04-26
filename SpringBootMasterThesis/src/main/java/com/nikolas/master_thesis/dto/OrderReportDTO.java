package com.nikolas.master_thesis.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor	
public class OrderReportDTO {

	private List<OrderDTO> orders;

	public OrderReportDTO() {
		this.orders = new ArrayList<>();
	}
	
	
	
	
}
