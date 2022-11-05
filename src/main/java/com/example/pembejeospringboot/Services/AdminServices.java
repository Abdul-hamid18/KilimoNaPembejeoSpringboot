package com.example.pembejeospringboot.Services;

import com.example.pembejeospringboot.DTO.AdminDTO;
import com.example.pembejeospringboot.DTO.AdminPasswordDTO;
import com.example.pembejeospringboot.Exceptions.ResourceNotFoundException;
import com.example.pembejeospringboot.Models.Admin;
import com.example.pembejeospringboot.Repositories.AdminRepository;
import lombok.Data;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Data
@Service

public class AdminServices {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Admin saveAdmin(Admin admin){
        return adminRepository.save(admin);
    }

    //get all admin
    public List<AdminDTO> viewAdmins(){
        List<AdminDTO> arrayList = new ArrayList<>();
        for (Admin admin : adminRepository.findAll()) {
            AdminDTO adminDTO = modelMapper.map(admin, AdminDTO.class);
            arrayList.add(adminDTO);
        }
        return arrayList;

    }

    //get admin by id
    public Optional<Admin> viewAdminById(Long id){
        return adminRepository.findById(id);
    }

    //update admin
    public Admin editAdmin(Long id,AdminDTO adminDTO)
    {
        Admin admin1 = adminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid Id selected"));

        Admin admin = modelMapper.map(adminDTO, Admin.class);

        admin1.setEmail(admin.getEmail());
        admin1.setRegNo(admin.getRegNo());
        admin1.setPhoneNo(admin.getPhoneNo());
        admin1.setFullName(admin.getFullName());

        return adminRepository.save(admin1);

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

    public Admin editPassword(Long id, AdminPasswordDTO adminPasswordDTO) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid Id selected"));

        Admin admin1 = modelMapper.map(adminPasswordDTO, Admin.class);

        admin.setPassword(admin1.getPassword());

        return adminRepository.save(admin);
    }


}
