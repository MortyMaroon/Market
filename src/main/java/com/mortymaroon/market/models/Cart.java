package com.mortymaroon.market.models;

import com.mortymaroon.market.dto.ProductDto;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Data
public class Cart {
    private List<ProductDto> listOfProduct;

    @PostConstruct
    private void initiation() {
        this.listOfProduct = new ArrayList<>();
    }
}
