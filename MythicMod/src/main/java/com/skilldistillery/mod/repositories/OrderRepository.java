package com.skilldistillery.mod.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.mod.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

}
