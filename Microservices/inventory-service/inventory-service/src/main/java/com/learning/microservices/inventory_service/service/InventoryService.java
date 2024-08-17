package com.learning.microservices.inventory_service.service;

import org.springframework.stereotype.Service;

import com.learning.microservices.inventory_service.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService {
   
	private final InventoryRepository inventoryRepo;
	
	public boolean isInStack(String skuCode ,Integer quantity) {
		return inventoryRepo.existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode,quantity);
	}
}
