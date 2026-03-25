package com.satva.satvafoods.repository;

import com.satva.satvafoods.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer>{

    List<Order> findByUserId(int userId);

}