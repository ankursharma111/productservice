package com.ankur.bms.productservice.dtos;


import lombok.*;

@Getter
@Setter
public class ProductDto {

    private Long id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;



}
