package com.ordenes.service;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ordenes.dto.OrderLineItemsDto;
import com.ordenes.dto.OrderRequest;
import com.ordenes.model.Order;
import com.ordenes.model.OrderLineItems;
import com.ordenes.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
	
	private final OrderRepository orderRepository;
	
	public void placeOrder(OrderRequest orderRequest) {
		Order order= new Order();
		order.setOrderNumber(UUID.randomUUID().toString());
		
		List<OrderLineItems> orderLineItems= orderRequest.getOrderLineItemsDtoList()
		 .stream()
		 .map(this::mapToDto)
		 .toList();
		
		order.setOrderLineList(orderLineItems);
		orderRepository.save(order);
	}
	
	private OrderLineItems mapToDto (OrderLineItemsDto orderLineItemsDto) {
		OrderLineItems orderLineItems= new OrderLineItems();
		orderLineItems.setPrice(orderLineItemsDto.getPrice());
		orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
		orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
		return orderLineItems;
	}

}
