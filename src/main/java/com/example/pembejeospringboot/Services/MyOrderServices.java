package com.example.pembejeospringboot.Services;

import com.example.pembejeospringboot.DTO.MyOrderRequestDTO;
import com.example.pembejeospringboot.DTO.MyOrderResponseDTO;
import com.example.pembejeospringboot.Exceptions.ResourceNotFoundException;
import com.example.pembejeospringboot.Models.Farmer;
import com.example.pembejeospringboot.Models.MyOrder;
import com.example.pembejeospringboot.Models.Product;
import com.example.pembejeospringboot.Models.Supplier;
import com.example.pembejeospringboot.Repositories.MyOrderRepository;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Data
@Service
public class MyOrderServices {
    @Autowired
    private MyOrderRepository myOrderRepository;

    @Autowired
    private ModelMapper modelMapper;

    // Add  order
    public MyOrder save(MyOrderRequestDTO myOrderReqDTO) {
        Farmer farmer = new Farmer();
        Product product = new Product();
        Supplier supplier = new Supplier();

        farmer.setFarmerId(myOrderReqDTO.getFarmerId());
        product.setProductId(myOrderReqDTO.getProductId());
        supplier.setSupplierId(myOrderReqDTO.getSupplierId());

        MyOrder myOrder = modelMapper.map(myOrderReqDTO, MyOrder.class);

        myOrder.setProduct(product);
        myOrder.setFarmer(farmer);
        myOrder.setSupplier(supplier);

        return myOrderRepository.save(myOrder);

    }

    // get order
    public List<MyOrderResponseDTO> getAll() {
        List<MyOrderResponseDTO> list = new ArrayList<>();
        for (MyOrder myOrder : myOrderRepository.findAll()) {

            MyOrderResponseDTO myOrderResponseDTO = modelMapper.map(myOrder, MyOrderResponseDTO.class);

            myOrderResponseDTO.setOrderDate(myOrder.getOrderDate());
            myOrderResponseDTO.setQuantity(myOrder.getQuantity());

            myOrderResponseDTO.setFarmerFName(myOrder.getFarmer().getFarmerFName());
            myOrderResponseDTO.setFarmerLName(myOrder.getFarmer().getFarmerLName());
            myOrderResponseDTO.setFarmerAddress(myOrder.getFarmer().getFarmerAddress());
            myOrderResponseDTO.setFarmerPhoneNo(myOrder.getFarmer().getFarmerPhoneNo());

            myOrderResponseDTO.setProductName(myOrder.getProduct().getProductName());
            myOrderResponseDTO.setProductPrice(myOrder.getProduct().getProductPrice());
            myOrderResponseDTO.setSupplierId(myOrder.getProduct().getSupplier().getSupplierId());

            list.add(myOrderResponseDTO);
        }
        return list;
    }

    // get order by id
    public Optional<MyOrder> viewOrderById(Long id) {
        return myOrderRepository.findById(id);
    }

    // delete order
    public ResponseEntity<Map<String, Boolean>> removeOrder(Long id) {
        MyOrder myOrder = myOrderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid Id Selected"));
        myOrderRepository.delete(myOrder);

        Map<String, Boolean> response = new HashMap<>();
        response.put("Is deleted?", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    // update order
    public ResponseEntity<MyOrder> editOrder(Long id, MyOrder myOrder) {
        MyOrder myOrder1 = myOrderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid Id Selected"));

        myOrder1.setQuantity(myOrder.getQuantity());
        myOrder1.setOrderDate(myOrder.getOrderDate());

        MyOrder myOrder2 = myOrderRepository.save(myOrder1);
        return ResponseEntity.ok(myOrder2);
    }

    public List<MyOrder> selectOrderBySupplierId(Long Id){
        return myOrderRepository.getBySupplierId(Id);
    }


}
