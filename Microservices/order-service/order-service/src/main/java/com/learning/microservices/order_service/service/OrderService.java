package com.learning.microservices.order_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.learning.microservices.order_service.client.InventoryClient;
import com.learning.microservices.order_service.dto.OrderRequest;
import com.learning.microservices.order_service.dto.OrderResponse;
import com.learning.microservices.order_service.entity.Order;
import com.learning.microservices.order_service.repository.OrderRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class OrderService {
	
	private final OrderRepository orderRepo;
	private final InventoryClient inventoryClient;
	
  public OrderResponse placeOrders(OrderRequest orderRequest) {
	boolean isInStock =   inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());
       
	if(isInStock) {
		
		Order order = Order.builder()
				.orderNumber(orderRequest.orderNumber())
				.skuCode(orderRequest.skuCode())
				.price(orderRequest.price())
				.quantity(orderRequest.quantity())
				.build();
		
		orderRepo.save(order);
		return new OrderResponse(order.getId(), order.getOrderNumber(), order.getSkuCode(), order.getPrice(), order.getQuantity());
	}
	else {
		throw new RuntimeException("Product with sku code" + orderRequest.skuCode()+" not available");
	}
  }
  
  public List<OrderResponse> getAllOrders() { 	
     	return orderRepo.findAll().stream()
     	.map(order -> new OrderResponse(order.getId(), order.getOrderNumber(), order.getSkuCode(), order.getPrice(), order.getQuantity()) )
     	.toList();
   }
}
