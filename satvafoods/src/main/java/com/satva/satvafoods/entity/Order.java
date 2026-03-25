package com.satva.satvafoods.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int userId;
    private double totalPrice;
    private String status;
    private LocalDateTime orderDate;

    public Order(){}

    public int getId(){ return id; }
    public void setId(int id){ this.id=id; }

    public int getUserId(){ return userId; }
    public void setUserId(int userId){ this.userId=userId; }

    public double getTotalPrice(){ return totalPrice; }
    public void setTotalPrice(double totalPrice){ this.totalPrice=totalPrice; }

    public String getStatus(){ return status; }
    public void setStatus(String status){ this.status=status; }

    public LocalDateTime getOrderDate(){ return orderDate; }
    public void setOrderDate(LocalDateTime orderDate){ this.orderDate=orderDate; }
}