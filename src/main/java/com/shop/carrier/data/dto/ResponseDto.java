package com.shop.carrier.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseDto {
    private Long productId; // productId 추가
    private String brand;
    private String productName;
    private String image;
    private int price;
    private double capacity;
    private String size;
}
