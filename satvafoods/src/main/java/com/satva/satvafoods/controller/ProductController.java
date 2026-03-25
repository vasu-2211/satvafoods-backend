package com.satva.satvafoods.controller;

import com.satva.satvafoods.entity.Product;
import com.satva.satvafoods.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Get all products
    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Add product
    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    // Update product
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product updatedProduct) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(updatedProduct.getName());
        product.setCategory(updatedProduct.getCategory());
        product.setPrice(updatedProduct.getPrice());
        product.setDescription(updatedProduct.getDescription());
        product.setImage_url(updatedProduct.getImage_url());
        product.setStock(updatedProduct.getStock());

        return productRepository.save(product);
    }

    // Delete product
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id) {

        productRepository.deleteById(id);

        return "Product deleted successfully";
    }
}