package com.acledabankplc.thirtpartyAPI;

import lombok.Data;

@Data
public class ProductResponse {
    private Long id;
    private String title;
    private Double price;
    private String description;
    private String image;
    private String category;
}
