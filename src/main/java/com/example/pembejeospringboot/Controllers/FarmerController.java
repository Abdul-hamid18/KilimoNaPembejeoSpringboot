package com.example.pembejeospringboot.Controllers;

import com.example.pembejeospringboot.DTO.FarmerRequestDTO;
import com.example.pembejeospringboot.DTO.FarmerRespoDTO;
import com.example.pembejeospringboot.Models.Farmer;
import com.example.pembejeospringboot.Services.FarmerServices;

import lombok.Data;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Data
@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class FarmerController {
    @Autowired
    FarmerServices farmerServices;

    @Autowired
    public ModelMapper modelMapper;

    @PostMapping("/farmer")
    public ResponseEntity<?> add(@RequestBody FarmerRequestDTO farmerRequestDTO) {
        Farmer farmer = new Farmer();
        farmer = modelMapper.map(farmerRequestDTO, Farmer.class);
        Farmer farmer2 = farmerServices.addFarmer(farmer);
        return ResponseEntity.ok().body(farmer2);
    }

    @GetMapping("/farmer")
    public ResponseEntity<?> getall() {
        List<FarmerRespoDTO> list = new ArrayList<>();
        FarmerRespoDTO farmerRespoDTO = new FarmerRespoDTO();
        for (Farmer farmer : farmerServices.viewFarmer()) {
            farmerRespoDTO = modelMapper.map(farmer, FarmerRespoDTO.class);
            list.add(farmerRespoDTO);
        }
        return ResponseEntity.ok(list);
    }

    @GetMapping("/farmer/{id}")
    public Optional<Farmer> viewFarmerById(@PathVariable Long id) {
        return farmerServices.viewFarmerById(id);
    }

    @DeleteMapping("/farmer/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteFarmer(@PathVariable Long id) {
        return ResponseEntity.ok(farmerServices.deleteFarmer(id).getBody());
    }

    @PutMapping("/farmer/{id}")
    public ResponseEntity<Farmer> updateFarmer(@PathVariable Long id, @RequestBody Farmer farmer) {
        return ResponseEntity.ok(farmerServices.updateFarmer(id, farmer).getBody());
    }

}
