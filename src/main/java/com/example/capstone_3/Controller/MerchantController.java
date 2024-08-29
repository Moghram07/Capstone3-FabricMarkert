package com.example.capstone_3.Controller;

import com.example.capstone_3.Model.Merchant;
import com.example.capstone_3.Model.Rating;
import com.example.capstone_3.Service.MerchantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//All made by Omar Alshehri
@RestController
@RequestMapping("/api/v1/merchant")
@RequiredArgsConstructor
public class MerchantController {

    private final MerchantService merchantService;

    @GetMapping("/get")
    public ResponseEntity getAllMerchant() {
        return ResponseEntity.ok(merchantService.getMerchant());
    }

    @PostMapping("/add")
    public ResponseEntity addMerchant(@RequestBody @Valid Merchant merchant) {
        merchantService.addMerchant(merchant);
        return ResponseEntity.status(200).body("Merchant added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchant(@PathVariable Integer id, @RequestBody @Valid Merchant merchant) {
        merchantService.updateMerchant(id, merchant);
        return ResponseEntity.status(200).body("Merchant updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchant(@PathVariable Integer id) {
        merchantService.deleteMerchant(id);
        return ResponseEntity.status(200).body("Merchant deleted");
    }

    @PutMapping("/accept/{orderId}/{merchantId}")
    public ResponseEntity acceptOrder(@PathVariable Integer orderId, @PathVariable Integer merchantId) {
        merchantService.acceptOrder(orderId, merchantId);
        return ResponseEntity.status(200).body("Order accepted successfully");
    }

    @PutMapping("/reject/{orderId}/{merchantId}")
    public ResponseEntity rejectOrder(@PathVariable Integer orderId, @PathVariable Integer merchantId) {
        merchantService.rejectOrder(orderId, merchantId);
        return ResponseEntity.status(200).body("Order accepted successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Merchant> getMerchantById(@PathVariable Integer id) {
        Merchant merchant = merchantService.getMerchantById(id);
        return ResponseEntity.ok(merchant);
    }

    @GetMapping("/ownerName/{ownerName}")
    public ResponseEntity<Merchant> getMerchantByOwnerName(@PathVariable String ownerName) {
        Merchant merchant = merchantService.getMerchantByOwnerName(ownerName);
        return ResponseEntity.ok(merchant);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Merchant> getMerchantByEmail(@PathVariable String email) {
        Merchant merchant = merchantService.getMerchantByEmail(email);
        return ResponseEntity.ok(merchant);
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<Merchant> getMerchantByPhone(@PathVariable String phone) {
        Merchant merchant = merchantService.getMerchantByPhone(phone);
        return ResponseEntity.ok(merchant);
    }

    //Average for Merchant
    @GetMapping("/average-rating/{id}")
    public ResponseEntity getMerchantAverage(@PathVariable Integer id) {
        Double averageRating = merchantService.getAverageRatingForMerchant(id);
        return ResponseEntity.status(200).body("The average is : "+averageRating);
    }

    //List of Ratings
    @GetMapping("/merchant-ratings/{merchantId}")
    public ResponseEntity getRatingsForMerchant(@PathVariable Integer merchantId) {
        List<Rating> ratings = merchantService.getRatingsForMerchant(merchantId);
        return ResponseEntity.status(200).body(ratings);
    }

    @GetMapping("/total/{merchantId}")
    public ResponseEntity getTotalRevenue(@PathVariable Integer merchantId) {
        return ResponseEntity.ok(merchantService.totalRevenue(merchantId));
    }


}