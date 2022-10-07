package com.example.pembejeospringboot.Models;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;
    private String regNo;
    private String fullName;
    private String email;
    private String phoneNo;
    private String password;


}
