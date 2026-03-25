package com.satva.satvafoods.service;

import com.satva.satvafoods.entity.*;
import com.satva.satvafoods.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    // USERS
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    // PRODUCTS
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(int id, Product updatedProduct) {

        Product product = productRepository.findById(id).orElseThrow();

        product.setName(updatedProduct.getName());
        product.setPrice(updatedProduct.getPrice());
        product.setDescription(updatedProduct.getDescription());
        product.setCategory(updatedProduct.getCategory());
        product.setStock(updatedProduct.getStock());

        // ✅ Correct method (matches your entity)
        product.setImage_url(updatedProduct.getImage_url());

        return productRepository.save(product);
    }

    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    // ORDERS
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order updateOrderStatus(int id, String status) {
        Order order = orderRepository.findById(id).orElseThrow();

        // ❌ DO NOT ALLOW UPDATE IF CANCELLED
        if ("CANCELLED".equals(order.getStatus())) {
            throw new RuntimeException("Cannot update cancelled order");
        }

        order.setStatus(status);
        return orderRepository.save(order);
    }
    public Product saveProductWithImage(String name, String category, double price,
                                        String description, int stock, MultipartFile file) {

        String fileName = file.getOriginalFilename();

        try {
            String uploadPath = System.getProperty("user.dir") + "/uploads/";

            java.io.File uploadDir = new java.io.File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            java.io.File saveFile = new java.io.File(uploadDir, fileName);
            file.transferTo(saveFile);

            System.out.println("Saved at: " + saveFile.getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
        }

        Product product = new Product();
        product.setName(name);
        product.setCategory(category);
        product.setPrice(price);
        product.setDescription(description);
        product.setStock(stock);
        product.setImage_url(fileName);

        return productRepository.save(product);
    }
    public long getUserCount() {
        return userRepository.count();
    }

    public long getProductCount() {
        return productRepository.count();
    }

    public long getOrderCount() {
        return orderRepository.count();
    }

}