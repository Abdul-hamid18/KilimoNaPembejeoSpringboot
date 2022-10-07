package com.example.pembejeospringboot.DTO;

import lombok.Data;

@Data
public class ProductResponseDTO {
    private Long productId;
    private String productName;
    private int productPrice;
    private String productDescr;
    private  String category;

    private Long supplierId;
    private String supplierFName;
    private String supplierLName;
    private String supplierAddress;
    private String supplierPhoneNo;
}
