package com.example.pembejeospringboot.Repositories;

import com.example.pembejeospringboot.Models.Supplier;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
  @Query( value="select * from supplier where supplier_email = ?1", nativeQuery = true)
  Supplier getByEmailAndPassword(String email);
}
