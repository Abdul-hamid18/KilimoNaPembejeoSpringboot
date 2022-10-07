package com.example.pembejeospringboot.Repositories;

import com.example.pembejeospringboot.Models.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmerRepository extends JpaRepository<Farmer, Long> { }
