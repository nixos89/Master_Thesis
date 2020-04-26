package com.nikolas.master_thesis.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class OrderDTO {

	private Long orderId;
	private String orderDate;
	private double orderPrice;
	private UserDTO user;
	@JsonProperty("order_items")
	private List<OrderItemDTO> orderItemDTOList;

	public OrderDTO() {
		this.orderItemDTOList = new ArrayList<>();
	}

}
