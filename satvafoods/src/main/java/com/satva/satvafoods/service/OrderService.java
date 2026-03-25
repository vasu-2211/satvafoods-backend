package com.satva.satvafoods.service;

import com.satva.satvafoods.entity.*;
import com.satva.satvafoods.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final EmailService emailService;

    public OrderService(OrderRepository orderRepository,
                        CartRepository cartRepository,
                        UserRepository userRepository,
                        ProductRepository productRepository,
                        EmailService emailService){

        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.emailService = emailService;
    }

    public Order placeOrder(int userId){

        List<Cart> cartItems = cartRepository.findByUserId(userId);

        if(cartItems.isEmpty()){
            throw new RuntimeException("Cart is empty");
        }

        double totalPrice = 0;
        List<String> productNames = new ArrayList<>();

        for(Cart cart : cartItems){

            Product product = productRepository.findById(cart.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            totalPrice += product.getPrice() * cart.getQuantity();

            productNames.add(product.getName());
        }

        Order order = new Order();
        order.setUserId(userId);
        order.setTotalPrice(totalPrice);
        order.setStatus("PLACED");
        order.setOrderDate(LocalDateTime.now());

        Order savedOrder = orderRepository.save(order);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        emailService.sendOrderConfirmation(
                user.getEmail(),
                savedOrder.getId(),
                productNames
        );

        cartRepository.deleteAll(cartItems);

        return savedOrder;
    }

    public List<Order> getUserOrders(int userId){
        return orderRepository.findByUserId(userId);
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    // ✅ CANCEL ORDER METHOD
    public void cancelOrder(int orderId){

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus("CANCELLED");

        orderRepository.save(order);
    }
}