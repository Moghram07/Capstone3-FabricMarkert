package com.example.capstone_3.Repository;

import com.example.capstone_3.Model.Tailor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TailorRepository extends JpaRepository<Tailor, Integer> {

    Tailor findTailorById(Integer id);
}