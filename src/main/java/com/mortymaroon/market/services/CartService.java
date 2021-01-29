package com.mortymaroon.market.services;

import com.mortymaroon.market.dto.ProductDto;
import com.mortymaroon.market.exception.ResourceNotfoundException;
import com.mortymaroon.market.models.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductService productService;
    private final Cart cart;

    public List<ProductDto> getAllProductsInCar() {
        return cart.getListOfProduct();
    }

    public void addProductInCar(Long id) {
        cart.getListOfProduct().add(productService.
                findProductById(id).
                orElseThrow(()-> new ResourceNotfoundException("Product with id: " + id + " doesn't exist")));
    }

    public void deleteProductFromCar(Long id) {
        cart.getListOfProduct().remove(productService.
                findProductById(id).
                orElseThrow(()-> new ResourceNotfoundException("Product with id: " + id + " doesn't exist")));
    }
}
