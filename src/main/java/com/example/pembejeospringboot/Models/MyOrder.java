package com.example.pembejeospringboot.Models;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table
public class MyOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private LocalDate orderDate;
    private int quantity;

    @ManyToOne()
    private Product product;

    @ManyToOne
    private Supplier supplier;

    @ManyToOne
    private Farmer farmer;




}
