package com.example.pembejeospringboot.DTO;

import java.time.LocalDate;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter

public class MyOrderRequestDTO {
    private  Long orderId;
    private LocalDate orderDate;
    private int quantity;

    private Long productId;

    private Long supplierId;

    private Long farmerId;



}
