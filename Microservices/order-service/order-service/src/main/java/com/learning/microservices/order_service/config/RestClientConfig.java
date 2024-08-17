package com.learning.microservices.order_service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.learning.microservices.order_service.client.InventoryClient;

@Configuration
public class RestClientConfig {
	
	@Value("${inventory.url}")
	private String inventoryServiceURL;
	
	@Bean
	public InventoryClient inventoryClient() {
		RestClient inventoryClient = RestClient.builder()
		  .baseUrl(inventoryServiceURL)
		  .build();
		
		RestClientAdapter resCliAdap = RestClientAdapter.create(inventoryClient);
		HttpServiceProxyFactory httpFactory = HttpServiceProxyFactory.builderFor(resCliAdap).build();
		return httpFactory.createClient(InventoryClient.class);
	}
}
