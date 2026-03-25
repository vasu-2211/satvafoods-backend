package com.satva.satvafoods.repository;

import com.satva.satvafoods.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Integer> {

    List<Cart> findByUserId(int userId);

    Optional<Cart> findByUserIdAndProductId(int userId,int productId);
}
