package com.learning.microservices.order_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.learning.microservices.order_service.dto.OrderRequest;
import com.learning.microservices.order_service.dto.OrderResponse;
import com.learning.microservices.order_service.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    
	private final OrderService orderService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	private OrderResponse placeOrder(@RequestBody OrderRequest orderRequest) {
		return orderService.placeOrders(orderRequest);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	private List<OrderResponse> getAllOrder() {
		return orderService.getAllOrders();
	}
}
