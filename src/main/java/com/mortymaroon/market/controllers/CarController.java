package com.mortymaroon.market.controllers;

import com.mortymaroon.market.dto.ProductDto;
import com.mortymaroon.market.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CarController {
    private final CartService cartService;

    @GetMapping
    public List<ProductDto> getAllProductsInCar() {
        System.out.println("prishlo");
        return cartService.getAllProductsInCar();
    }

    @PostMapping("/{id}")
    public void addProductInCar(@PathVariable Long id) {
        cartService.addProductInCar(id);
    }

    @DeleteMapping("/{id}")
    public void deleteProductFromCar(@PathVariable Long id) {
        cartService.deleteProductFromCar(id);
    }
}
