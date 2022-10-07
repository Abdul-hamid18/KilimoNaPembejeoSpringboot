package com.example.pembejeospringboot.Repositories;

import com.example.pembejeospringboot.Models.Admin;
import com.example.pembejeospringboot.Models.MyOrder;
import com.example.pembejeospringboot.Models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MyOrderRepository extends JpaRepository<MyOrder, Long> {

    @Query(value = "Select * from my_order m where supplier_supplier_id =?1", nativeQuery = true)
    List<MyOrder> getBySupplierId(Long id);

}
