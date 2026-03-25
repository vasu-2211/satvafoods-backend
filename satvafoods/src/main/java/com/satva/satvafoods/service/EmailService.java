package com.satva.satvafoods.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender){
        this.mailSender = mailSender;
    }

    public void sendOrderConfirmation(String toEmail,int orderId,List<String> productNames){

        StringBuilder productList = new StringBuilder();

        for(String name : productNames){
            productList.append("- ").append(name).append("\n");
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Order Confirmation - Satva Foods");

        message.setText(
                "Your order has been placed successfully.\n\n"+
                        "Order ID: "+orderId+"\n\n"+
                        "Products:\n"+
                        productList+
                        "\nThank you for choosing Satva Foods 🌿"
        );

        mailSender.send(message);
    }
}