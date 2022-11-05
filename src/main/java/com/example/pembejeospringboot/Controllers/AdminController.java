package com.example.pembejeospringboot.Controllers;

import com.example.pembejeospringboot.DTO.AdminDTO;
import com.example.pembejeospringboot.DTO.AdminPasswordDTO;
import com.example.pembejeospringboot.Models.Admin;
import com.example.pembejeospringboot.Repositories.AdminRepository;
import com.example.pembejeospringboot.Services.AdminServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class AdminController {
    @Autowired
    AdminServices adminServices;

    @Autowired
    AdminRepository adminRepository;

    @PostMapping("/admin")
    public ResponseEntity<?> postToAdmin(@RequestBody Admin admin){
        // return new ResponseEntity<>(adminServices.saveAdmin(admin), HttpStatus.CREATED);
        return ResponseEntity.ok(adminServices.saveAdmin(admin));
    }

    @GetMapping("/admin")

    public ResponseEntity<?> getAllAdmins(){
        return ResponseEntity.ok(adminServices.viewAdmins());
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<?> getAdminById(@PathVariable Long id){
        return ResponseEntity.ok(adminServices.viewAdminById(id));
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable Long id)
    {
        return ResponseEntity.ok(adminServices.removeAdmin(id).getBody());
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<?> updateAdmin(@PathVariable Long id, @RequestBody AdminDTO adminDTO)
    {
        return ResponseEntity.ok(adminServices.editAdmin(id, adminDTO));
    }

    @PostMapping("/admin/login")
    public ResponseEntity<?> getByAdminEmail(@RequestBody Admin admin){
        Admin admin1 = adminRepository.getByEmail(admin.getEmail());
        if(admin1.getPassword().equals(admin.getPassword())){
            return  ResponseEntity.ok(admin1);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

    }


    @PutMapping("/admin/password/{id}")
    public ResponseEntity<?> updateAdminPassword(@PathVariable Long id, @RequestBody AdminPasswordDTO adminPasswordDTO) {
        return  ResponseEntity.ok(adminServices.editPassword(id,adminPasswordDTO));
    }



}
