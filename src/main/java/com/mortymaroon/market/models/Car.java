package com.mortymaroon.market.models;

import com.mortymaroon.market.dto.ProductDto;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
public class Car {
    private List<ProductDto> listOfProduct = new ArrayList<>();

}
