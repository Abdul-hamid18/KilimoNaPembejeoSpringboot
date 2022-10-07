package com.example.pembejeospringboot.Models;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table
public class Farmer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long farmerId;
    private String farmerRegNo;
    private String farmerFName;
    private String farmerLName;
    private String category;
    private String farmerAddress;
    private String farmerPhoneNo;
    private String farmerEmail;
    private String farmerPassword;


}
