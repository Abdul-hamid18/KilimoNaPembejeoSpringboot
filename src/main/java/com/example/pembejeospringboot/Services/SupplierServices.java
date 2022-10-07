package com.example.pembejeospringboot.Services;

import com.example.pembejeospringboot.DTO.SupplierRequestDTO;
import com.example.pembejeospringboot.DTO.SupplierResponseDTO;
import com.example.pembejeospringboot.Exceptions.ResourceNotFoundException;
import com.example.pembejeospringboot.Models.Supplier;
import com.example.pembejeospringboot.Repositories.SupplierRepository;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Data
@Service
public class SupplierServices {
    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private ModelMapper modelMapper;

    // Add supplier
    public Supplier addSupplier(SupplierRequestDTO supplierRequestDTO) {
        Supplier supplier = modelMapper.map(supplierRequestDTO, Supplier.class);
        return supplierRepository.save(supplier);
    }


    // get all suppliers
    public List<SupplierResponseDTO> viewAllSuppliers() {

        List<SupplierResponseDTO> arrayList = new ArrayList<>();
        for (Supplier supplier : supplierRepository.findAll()) {
            SupplierResponseDTO supplierResponseDTO = modelMapper.map(supplier, SupplierResponseDTO.class);
            arrayList.add(supplierResponseDTO);
        }
        return arrayList;
    }

    // get supplier by id
    public Optional<Supplier> viewSupplierById(Long id) {
        return supplierRepository.findById(id);
    }

    // delete supplier
    public ResponseEntity<Map<String, Boolean>> removeSupplier(Long id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid Id Selected"));
        supplierRepository.delete(supplier);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Is deleted?", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    // update supplier
    public Supplier editSupplier(Long id, SupplierRequestDTO supplierRequestDTO) {
        Supplier supplier1 = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid Id selected"));

        Supplier supplier = modelMapper.map(supplierRequestDTO, Supplier.class);

        supplier1.setSupplierAddress(supplier.getSupplierAddress());
        supplier1.setSupplierEmail(supplier.getSupplierEmail());
        supplier1.setSupplierPassword(supplier.getSupplierPassword());
        supplier1.setSupplierRegNo(supplier.getSupplierRegNo());
        supplier1.setSupplierPhoneNo(supplier.getSupplierPhoneNo());
        supplier1.setSupplierFName(supplier.getSupplierFName());
        supplier1.setSupplierLName(supplier.getSupplierLName());


        return supplierRepository.save(supplier1);
    }

}