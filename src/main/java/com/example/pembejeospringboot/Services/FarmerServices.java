package com.example.pembejeospringboot.Services;

import com.example.pembejeospringboot.Exceptions.ResourceNotFoundException;
import com.example.pembejeospringboot.Models.Farmer;
import com.example.pembejeospringboot.Repositories.FarmerRepository;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Data
@Service
public class FarmerServices {
    @Autowired
    private FarmerRepository farmerRepository;



    // Add farmer
    public Farmer addFarmer(Farmer farmer){
        return farmerRepository.save(farmer);
    }

    //get all farmers
    public List<Farmer> viewFarmer(){
        return farmerRepository.findAll();
    }

    //get farmer by id
    public Optional<Farmer> viewFarmerById(Long id){
        return  farmerRepository.findById(id);
    }

    //delete farmer
    public ResponseEntity<Map<String,Boolean>> deleteFarmer(Long id)
    {
        Farmer farmer = farmerRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Invalid Farmer Id selected"));
        farmerRepository.delete(farmer);

        Map<String,Boolean> response = new HashMap<>();
        response.put("Is Farmer Deleted?",Boolean.TRUE);
        return ResponseEntity.ok(response);

    }


    //update farmer
    public ResponseEntity<Farmer> updateFarmer(Long id,Farmer farmer)
    {
        Farmer farmer1 = farmerRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Invalid Farmer Id Selected"));

        farmer1.setCategory(farmer.getCategory());
        farmer1.setFarmerEmail(farmer.getFarmerEmail());
        farmer1.setFarmerPassword(farmer.getFarmerPassword());
        farmer1.setFarmerAddress(farmer.getFarmerAddress());
        farmer1.setFarmerFName(farmer.getFarmerFName());
        farmer1.setFarmerLName(farmer.getFarmerLName());
        farmer1.setFarmerPhoneNo(farmer.getFarmerPhoneNo());
        farmer1.setFarmerRegNo(farmer.getFarmerRegNo());

        Farmer farmer2 = farmerRepository.save(farmer1);
        return ResponseEntity.ok(farmer2);

    }

}
