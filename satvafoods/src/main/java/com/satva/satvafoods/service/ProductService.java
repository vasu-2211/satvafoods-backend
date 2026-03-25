package com.satva.satvafoods.service;

import com.satva.satvafoods.entity.Product;
import com.satva.satvafoods.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product addProduct(Product product){
        return productRepository.save(product);
    }

    public Product updateProduct(int id, Product updatedProduct){

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

    public void deleteProduct(int id){
        productRepository.deleteById(id);
    }
}