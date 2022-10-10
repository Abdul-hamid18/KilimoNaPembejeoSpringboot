package com.example.pembejeospringboot.Controllers;

import com.example.pembejeospringboot.DTO.ProductRequestDTO;
import com.example.pembejeospringboot.Services.ProductServices;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Data
@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class ProductController {
    @Autowired
    ProductServices productServices;

    @PostMapping("/product")
    public ResponseEntity<?> postToProduct(@RequestBody ProductRequestDTO productRequestDTO){
        return ResponseEntity.ok(productServices.addProduct(productRequestDTO));
    }

    @GetMapping("/product")
    public ResponseEntity<?> getAllProducts(){
        return ResponseEntity.ok(productServices.viewProducts());
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<?> viewProductById(@PathVariable Long id){
        return ResponseEntity.ok(productServices.viewProductById(id));
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        return ResponseEntity.ok(productServices.removeProduct(id).getBody());

    }

    @PutMapping("/product/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody ProductRequestDTO productRequestDTO){
        return ResponseEntity.ok(productServices.editProduct(id,productRequestDTO));
    }

    @GetMapping("/product/supplier/{Id}")
    public ResponseEntity<?> getBySupplierId(@PathVariable Long Id){
        return ResponseEntity.ok(productServices.selectProductBySupplierId(Id));
    }

    @GetMapping("/product/category")
    public ResponseEntity<?> getByCategory(@RequestParam String category){
        return ResponseEntity.ok(productServices.selectProductByCategory(category));
    }


}
