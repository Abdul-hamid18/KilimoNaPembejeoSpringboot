package com.example.pembejeospringboot.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class SupplierResponseDTO {
    private Long supplierId;
    private String supplierFName;
    private String supplierLName;
    private String supplierRegNo;
    private String supplierAddress;
    private String supplierPhoneNo;
    private String supplierEmail;

}
