package com.nikolas.master_thesis.service;

import com.nikolas.master_thesis.dto.OrderListDTO;
import com.nikolas.master_thesis.dto.OrderReportDTO;
import com.nikolas.master_thesis.dto.OrderResponseDTO;

public interface OrderService {

	OrderResponseDTO addOrder(OrderListDTO orderRequest, String username);
	
	OrderReportDTO getAllOrders();
	
}
