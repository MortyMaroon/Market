package com.mortymaroon.market.services;

import com.mortymaroon.market.dto.ProductDto;
import com.mortymaroon.market.exception.ResourceNotfoundException;
import com.mortymaroon.market.models.Car;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductService productService;
    @Autowired
    private Car car;

    public List<ProductDto> getAllProductsInCar() {
        return car.getListOfProduct();
    }

    public void addProductInCar(Long id) {
        car.getListOfProduct().add(productService.
                findProductById(id).
                orElseThrow(()-> new ResourceNotfoundException("Product with id: " + id + " doesn't exist")));
    }

    public void deleteProductFromCar(Long id) {
        car.getListOfProduct().remove(productService.
                findProductById(id).
                orElseThrow(()-> new ResourceNotfoundException("Product with id: " + id + " doesn't exist")));
    }
}
