package com.satva.satvafoods.controller;

import com.satva.satvafoods.entity.Order;
import com.satva.satvafoods.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping("/place/{userId}")
    public Map<String,Object> placeOrder(@PathVariable int userId){

        Order order = orderService.placeOrder(userId);

        Map<String,Object> response = new HashMap<>();
        response.put("message","Order placed successfully");
        response.put("orderId",order.getId());

        return response;
    }

    @GetMapping("/user/{userId}")
    public List<Order> getUserOrders(@PathVariable int userId){
        return orderService.getUserOrders(userId);
    }

    @GetMapping
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

    // ⭐ CANCEL ORDER API
    @PutMapping("/cancel/{orderId}")
    public String cancelOrder(@PathVariable int orderId){
        orderService.cancelOrder(orderId);
        return "Order cancelled successfully";
    }

}