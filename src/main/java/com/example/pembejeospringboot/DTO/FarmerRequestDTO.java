package com.example.pembejeospringboot.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class FarmerRequestDTO {
    private String farmerRegNo;
    private String farmerFName;
    private String farmerLName;
    private String category;
    private String farmerAddress;
    private String farmerPhoneNo;
    private String farmerEmail;
    private String farmerPassword;
}
