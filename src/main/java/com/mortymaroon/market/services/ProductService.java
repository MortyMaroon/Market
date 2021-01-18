package com.mortymaroon.market.services;

import com.mortymaroon.market.dto.ProductDto;
import com.mortymaroon.market.models.Product;
import com.mortymaroon.market.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Optional<ProductDto> findProductById(Long id) {
        return productRepository.findById(id).stream().map(ProductDto::new).findFirst();
    }

    public List<ProductDto> findAll() {
        return productRepository.findAll().stream().map(ProductDto::new).collect(Collectors.toList());
    }

    public Page<ProductDto> findAll(int page) {
        Page<Product> originalPage = productRepository.findAll(PageRequest.of(page - 1, 5));
        return new PageImpl<>(originalPage.getContent().stream().map(ProductDto::new).collect(Collectors.toList()),
                originalPage.getPageable(),
                originalPage.getTotalElements());
    }

    public ProductDto saveOrUpdate(ProductDto productDto) {
        if (productDto.getId() == null) {
            return new ProductDto(productRepository.save(setProductFields(productDto, new Product())));
        } else {
            return new ProductDto(productRepository.save(setProductFields(productDto, productRepository.findById(productDto.getId()).get())));
        }
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    private Product setProductFields(ProductDto src, Product dst) {
        dst.setTitle(src.getTitle());
        dst.setPrice(src.getPrice());
        return dst;
    }
}
