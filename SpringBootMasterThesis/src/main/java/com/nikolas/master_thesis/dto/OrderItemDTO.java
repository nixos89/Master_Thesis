package com.nikolas.master_thesis.dto;

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
public class OrderItemDTO {

	private Long orderItemId;
	private int orderedAmount;
	private BookDTO book;
	private Long orderId;
	private double totalOrderItemPrice;
	
}
