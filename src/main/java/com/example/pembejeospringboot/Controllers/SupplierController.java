package com.example.pembejeospringboot.Controllers;

import com.example.pembejeospringboot.DTO.ProductRequestDTO;
import com.example.pembejeospringboot.DTO.SupplierPasswordDTO;
import com.example.pembejeospringboot.DTO.SupplierRequestDTO;
import com.example.pembejeospringboot.DTO.SupplierResponseDTO;
import com.example.pembejeospringboot.Models.Supplier;
import com.example.pembejeospringboot.Repositories.SupplierRepository;
import com.example.pembejeospringboot.Services.SupplierServices;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Data
@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class SupplierController {
    @Autowired
    SupplierServices supplierServices;

    @Autowired
    SupplierRepository supplierRepository;

    @PostMapping("/supplier")
    public ResponseEntity<?> postToSupplier(@RequestBody SupplierRequestDTO supplierRequestDTO){
        return ResponseEntity.ok(supplierServices.addSupplier(supplierRequestDTO));
    }

    @GetMapping("/supplier")
    public ResponseEntity<?> getAllSuppliers(){
        return ResponseEntity.ok((supplierServices.viewAllSuppliers()));
    }

    @GetMapping("/supplier/{id}")
    public ResponseEntity<?> viewSupplierById(@PathVariable Long id){
        return ResponseEntity.ok(supplierServices.viewSupplierById(id));
    }

    @DeleteMapping("/supplier/{id}")
    public ResponseEntity<?> deleteSupplier(@PathVariable Long id) {
        return ResponseEntity.ok(supplierServices.removeSupplier(id).getBody());
    }

    @PutMapping("/supplier/{id}")
    public ResponseEntity<?> updateSupplier(@PathVariable Long id, @RequestBody SupplierResponseDTO supplierResponseDTO) {
        return  ResponseEntity.ok(supplierServices.editSupplier(id,supplierResponseDTO));
    }


    @PostMapping("/supplier/login")
    public ResponseEntity<?> getBySupplierId(@RequestBody Supplier supplier){
        Supplier supplier1 = supplierRepository.getByEmailAndPassword(supplier.getSupplierEmail());
        if(supplier1.getSupplierPassword().equals(supplier.getSupplierPassword())){
            return  ResponseEntity.ok(supplier1);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

    }

    @PutMapping("/supplier/password/{id}")
    public ResponseEntity<?> updateSupplierPassword(@PathVariable Long id, @RequestBody SupplierPasswordDTO supplierPasswordDTO) {
        return  ResponseEntity.ok(supplierServices.editPassword(id,supplierPasswordDTO));
    }



}
