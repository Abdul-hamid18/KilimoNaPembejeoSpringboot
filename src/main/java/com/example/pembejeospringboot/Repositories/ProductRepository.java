package com.example.pembejeospringboot.Repositories;

import com.example.pembejeospringboot.Models.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query(value = "Select * from product p where supplier_supplier_id =?1", nativeQuery = true)
    List<Product> getProductBySupplierId(Long Id);

    @Query(value = "select * from product where category=?1", nativeQuery = true)
    List<Product> getByCategory(String category);

}

