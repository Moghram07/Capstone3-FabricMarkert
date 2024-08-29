package com.example.capstone_3.Repository;

import com.example.capstone_3.Model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//All made by Omar Alshehri
@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Integer> {

    Merchant findMerchantById(Integer id);

    Merchant findByOwnerName(String ownerName);

    Merchant findByEmail(String email);

    Merchant findByPhone(String phone);
}
