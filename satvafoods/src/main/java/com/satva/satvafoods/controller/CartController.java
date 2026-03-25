package com.satva.satvafoods.controller;

import com.satva.satvafoods.entity.Cart;
import com.satva.satvafoods.service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public Cart addToCart(@RequestBody Cart cart){

        return cartService.addToCart(
                cart.getUserId(),
                cart.getProductId(),
                cart.getQuantity()
        );
    }

    @GetMapping("/{userId}")
    public List<Cart> getUserCart(@PathVariable int userId){
        return cartService.getUserCart(userId);
    }
    @DeleteMapping("/{cartId}")
    public String removeFromCart(@PathVariable int cartId){
        cartService.removeFromCart(cartId);
        return "Item removed from cart";
    }
    @PutMapping("/{cartId}")
    public Cart updateCartQuantity(@PathVariable int cartId, @RequestBody Cart cart){

        return cartService.updateCartQuantity(cartId, cart.getQuantity());
    }
    @PutMapping("/update/{cartId}")
    public Cart updateQuantity(@PathVariable int cartId, @RequestParam int quantity){
        return cartService.updateCartQuantity(cartId, quantity);
    }


}