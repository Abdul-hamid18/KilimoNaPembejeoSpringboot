package com.example.pembejeospringboot.DTO;

import java.time.LocalDate;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
@Setter
@Getter
public class MyOrderResponseDTO {
    private Long orderId;
    private LocalDate orderDate;
    private int quantity;

    private Long supplierId;

    private String farmerFName;
    private String farmerLName;
    private String farmerAddress;
    private String farmerPhoneNo;

    private Long productId;
    private String productName;
    private int productPrice;
    private String category;

}
