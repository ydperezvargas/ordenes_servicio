package com.ordenes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ordenes.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
