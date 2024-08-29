package com.example.capstone_3.Repository;

import com.example.capstone_3.Model.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
//All made by Omar Alshehri
@Repository
public interface ShippingRepository extends JpaRepository<Shipping, Integer> {
    Shipping findShippingById(Integer id);
    List<Shipping> findShippingByStatus(String status);
}
