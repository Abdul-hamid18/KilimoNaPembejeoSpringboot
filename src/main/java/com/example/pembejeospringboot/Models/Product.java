package com.example.pembejeospringboot.Models;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String productName;
    private int productPrice;
    private String productDescr;
    private String category;

    @ManyToOne
    private Supplier supplier;

}
