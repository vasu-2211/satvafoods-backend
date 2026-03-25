package com.satva.satvafoods.service;

import com.satva.satvafoods.entity.Cart;
import com.satva.satvafoods.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    // Add product to cart
    public Cart addToCart(int userId, int productId, int quantity) {

        Optional<Cart> existingCart =
                cartRepository.findByUserIdAndProductId(userId, productId);

        if(existingCart.isPresent()) {

            Cart cart = existingCart.get();

            // increase quantity if already exists
            cart.setQuantity(cart.getQuantity() + quantity);

            return cartRepository.save(cart);

        } else {

            Cart cart = new Cart();
            cart.setUserId(userId);
            cart.setProductId(productId);
            cart.setQuantity(quantity);

            return cartRepository.save(cart);
        }
    }

    // Get all cart items for a user
    public List<Cart> getUserCart(int userId){
        return cartRepository.findByUserId(userId);
    }

    // Remove item from cart
    public void removeFromCart(int cartId){
        cartRepository.deleteById(cartId);
    }

    // Update quantity
    public Cart updateCartQuantity(int cartId, int quantity){

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        cart.setQuantity(quantity);

        return cartRepository.save(cart);
    }
}
