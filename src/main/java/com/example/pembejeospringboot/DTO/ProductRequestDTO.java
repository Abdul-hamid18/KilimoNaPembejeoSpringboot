package com.example.pembejeospringboot.DTO;

import lombok.Data;

@Data
public class ProductRequestDTO {
    private Long productId;
    private  String productName;
    private int productPrice;
    private String productDescr;
    private String category;
    private Long supplierId;
 
}
