package com.nikolas.master_thesis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nikolas.master_thesis.dto.OrderListDTO;
import com.nikolas.master_thesis.dto.OrderReportDTO;
import com.nikolas.master_thesis.dto.OrderResponseDTO;
import com.nikolas.master_thesis.service.OrderService;

import io.micrometer.core.annotation.Timed;

@RestController
@RequestMapping("api/orders")
public class OrderController {

	@Autowired
	OrderService orderService;

	@GetMapping
	@Timed("getAllOrders.requests")
	public ResponseEntity<OrderReportDTO> getAllOrders() {
		return ResponseEntity.ok(orderService.getAllOrders());
	}

	@PostMapping
	@Timed("createOrder.requests")
	public ResponseEntity<OrderResponseDTO> addOrder(@RequestBody OrderListDTO orderRequest, @RequestParam(name= "username") String username) {
		return new ResponseEntity<OrderResponseDTO>(orderService.addOrder(orderRequest, username), HttpStatus.OK);
	}

}
