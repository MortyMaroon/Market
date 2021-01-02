package com.mortymaroon.market.controllers;

import com.mortymaroon.market.models.Product;
import com.mortymaroon.market.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<Product> findAllProducts(
            @RequestParam(name = "min_price", defaultValue = "0") Integer minPrice,
            @RequestParam(name = "max_price", required = false) Integer maxPrice,
            @RequestParam(name = "page", defaultValue = "0") Integer page
    ) {
        if (maxPrice == null) maxPrice = Integer.MAX_VALUE;
        return productService.findAllByPriceWithPagination(minPrice, maxPrice, page);
    }

    @GetMapping("/{id}")
    public Product findProductById(@PathVariable Long id) {
        return productService.findProductById(id).get();
    }

    @PostMapping
    public Product saveNewProduct(@RequestBody Product product) {
        product.setId(null);
        return productService.saveOrUpdate(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
    }
}
