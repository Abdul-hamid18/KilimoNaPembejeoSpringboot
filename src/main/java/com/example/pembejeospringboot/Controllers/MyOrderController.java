package com.example.pembejeospringboot.Controllers;

import com.example.pembejeospringboot.DTO.MyOrderRequestDTO;
import com.example.pembejeospringboot.Models.MyOrder;
import com.example.pembejeospringboot.Services.MyOrderServices;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Data
@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class MyOrderController {
    @Autowired
    MyOrderServices myOrderServices;

    @PostMapping("/order")
    public ResponseEntity<?> save(@RequestBody MyOrderRequestDTO myOrderReqDTO){
        return ResponseEntity.ok(myOrderServices.save(myOrderReqDTO));  
    }

    @GetMapping("/order")
    public ResponseEntity<?> getall(){
        return ResponseEntity.ok(myOrderServices.getAll());
    }


    @GetMapping("/myOrder/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Long id){
        return ResponseEntity.ok(myOrderServices.viewOrderById(id));
    }

    @DeleteMapping("/myOrder/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id){
        return  ResponseEntity.ok(myOrderServices.removeOrder(id).getBody());
    }

    @PutMapping("myOrder/{id}")
    public ResponseEntity<MyOrder> updateMyOrder(@PathVariable Long id, @RequestBody MyOrder myOrder){
       return ResponseEntity.ok(myOrderServices.editOrder(id,myOrder).getBody());
    }

    @GetMapping("/myOrder/supplier/{Id}")
    public ResponseEntity<?> getBySuplierId(@PathVariable Long Id){
        return ResponseEntity.ok(myOrderServices.selectOrderBySupplierId(Id));
    }

}