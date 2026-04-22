package com.food.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.food.models.OrderItem;

public interface OrderitemRepository extends JpaRepository<OrderItem,Long> {

}
