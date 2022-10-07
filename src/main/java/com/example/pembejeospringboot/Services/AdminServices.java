package com.example.pembejeospringboot.Services;

import com.example.pembejeospringboot.Exceptions.ResourceNotFoundException;
import com.example.pembejeospringboot.Models.Admin;
import com.example.pembejeospringboot.Repositories.AdminRepository;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Data
@Service

public class AdminServices {
    @Autowired
    private AdminRepository adminRepository;

    public Admin saveAdmin(Admin admin){
        return adminRepository.save(admin);
    }

    //get all admin
    public List<Admin> viewAdmins(){
        return adminRepository.findAll();
    }

    //get admin by id
    public Optional<Admin> viewAdminById(Long id){
        return adminRepository.findById(id);
    }

    //update admin
    public ResponseEntity<Admin> editAdmin(Long id,Admin admin)
    {
        Admin admin1 = adminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid Id selected"));
        admin1.setEmail(admin.getEmail());
        admin1.setPassword(admin.getPassword());
        admin1.setRegNo(admin.getRegNo());
        admin1.setPhoneNo(admin.getPhoneNo());
        admin1.setFullName(admin.getFullName());

        Admin admin2 = adminRepository.save(admin1);
        return ResponseEntity.ok(admin2);
    }

    // delete admin
    public ResponseEntity<Map<String,Boolean>> removeAdmin(Long id)
    {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid Id"));
        adminRepository.delete(admin);

        Map<String,Boolean> response = new HashMap<>();
        response.put("Is Admin Deleted?",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }



}
