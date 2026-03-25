package com.satva.satvafoods.controller;

import com.satva.satvafoods.entity.*;
import com.satva.satvafoods.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // USERS
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return adminService.getAllUsers();
    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable int id) {
        adminService.deleteUser(id);
        return "User deleted successfully";
    }

    // PRODUCTS
    @PostMapping("/product")
    public Product addProduct(
            @RequestParam String name,
            @RequestParam String category,
            @RequestParam double price,
            @RequestParam String description,
            @RequestParam int stock,
            @RequestParam("file") MultipartFile file) {

        return adminService.saveProductWithImage(name, category, price, description, stock, file);
    }

    @PutMapping("/product/{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product product) {
        return adminService.updateProduct(id, product);
    }

    @DeleteMapping("/product/{id}")
    public String deleteProduct(@PathVariable int id) {
        adminService.deleteProduct(id);
        return "Product deleted";
    }

    // ORDERS
    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return adminService.getAllOrders();
    }

    @PutMapping("/order/{id}")
    public Order updateOrder(@PathVariable int id, @RequestParam String status) {
        return adminService.updateOrderStatus(id, status);
    }
    @GetMapping("/dashboard")
    public Map<String, Long> getDashboardData() {

        Map<String, Long> data = new HashMap<>();

        data.put("users", adminService.getUserCount());
        data.put("products", adminService.getProductCount());
        data.put("orders", adminService.getOrderCount());

        return data;
    }
}