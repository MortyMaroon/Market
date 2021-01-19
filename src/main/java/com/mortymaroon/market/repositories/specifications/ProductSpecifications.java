package com.mortymaroon.market.repositories.specifications;

import com.mortymaroon.market.models.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.MultiValueMap;


public class ProductSpecifications {
    private static Specification<Product> priceGreaterOrEqualThan(int minPrice) {
        return (Specification<Product>)(root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"),minPrice);
    }

    private static Specification<Product> priceLesserOrEqualThan(int maxPrice) {
        return (Specification<Product>)(root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"),maxPrice);
    }

    private static Specification<Product> titleLike(String title) {
        return (Specification<Product>)(root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%",title));
    }

    public static Specification<Product> build(MultiValueMap<String,String> params) {
        Specification<Product> spec = Specification.where(null);
        if (params.containsKey("min_price") && !params.getFirst("min_price").isBlank()) {
            spec = spec.and(ProductSpecifications.priceGreaterOrEqualThan(Integer.valueOf(params.getFirst("min_price"))));
        }
        if (params.containsKey("max_price") && !params.getFirst("max_price").isBlank()) {
            spec = spec.and(ProductSpecifications.priceLesserOrEqualThan(Integer.valueOf(params.getFirst("max_price"))));
        }
        if (params.containsKey("title") && !params.getFirst("title").isBlank()) {
            spec = spec.and(ProductSpecifications.titleLike(params.getFirst("title")));
        }
        return spec;
    }
}
