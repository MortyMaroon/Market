package com.mortymaroon.market.services;

import com.mortymaroon.market.models.Product;
import com.mortymaroon.market.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> findAllProduct() {
        return productRepository.findAll();
    }

    public List<Product> findAllByPrice(int min, int max) {
        return productRepository.findAllByPriceBetween(min,max);
    }

    public List<Product> findAllByPriceWithPagination(int min, int max, int page) {
        return productRepository.findAllByPriceBetween(min,max, PageRequest.of(page,10)).getContent();
    }

    public Product saveOrUpdate(Product product) {
        return productRepository.save(product);
    }

    public void deleteProductById(Long id) { productRepository.deleteById(id);}
}
