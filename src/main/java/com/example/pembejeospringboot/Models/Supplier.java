package com.example.pembejeospringboot.Models;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supplierId;
    private String supplierFName;
    private String supplierLName;
    private String supplierRegNo;
    private String supplierAddress;
    private String supplierPhoneNo;
    private String supplierEmail;
    private String supplierPassword;

}
