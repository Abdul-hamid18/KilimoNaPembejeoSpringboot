package com.example.pembejeospringboot.Repositories;

import com.example.pembejeospringboot.Models.Admin;
import com.example.pembejeospringboot.Models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    @Query( value="select * from admin where email = ?1", nativeQuery = true)
    Admin getByEmail(String email);
    
}
