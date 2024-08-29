package com.example.capstone_3.Controller;


import com.example.capstone_3.Model.Rating;
import com.example.capstone_3.Service.RatingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rating")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;

    //Get
    @GetMapping("/get")
    public ResponseEntity getAllRatings() {
        return ResponseEntity.status(200).body(ratingService.getAllRatings());
    }

    //Add
    @PostMapping("/add")
    public ResponseEntity addRating(@Valid @RequestBody Rating rating) {
        ratingService.addRating(rating);
        return ResponseEntity.status(200).body("Rating added successfully");
    }

    //Update
    @PutMapping("/update/{id}")
    public ResponseEntity updateRating(@PathVariable Integer id, @Valid @RequestBody Rating rating) {
        ratingService.updateRating(id, rating);
        return ResponseEntity.status(200).body("Rating updated successfully");
    }

    //Delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteRating(@PathVariable Integer id) {
        ratingService.deleteRating(id);
        return ResponseEntity.status(200).body("Rating deleted successfully");
    }

    // Endpoint to rate a merchant
    @PostMapping("/merchant/{merchantId}/rate")
    public ResponseEntity rateMerchants(@PathVariable Integer merchantId,@Valid @RequestBody Rating rating) {
        ratingService.rateMerchant(merchantId, rating);
        return ResponseEntity.status(200).body("Rating Added successfully");
    }

    // Endpoint to rate a tailor
    @PostMapping("/tailor/{tailorId}/rate")
    public ResponseEntity rateTailors(@PathVariable Integer tailorId,@Valid @RequestBody Rating rating) {
        ratingService.rateTailor(tailorId, rating);
        return ResponseEntity.status(200).body("Rating Added successfully");
    }

    // Endpoint to rate a Designer
    @PostMapping("/designer/{designerId}/rate")
    public ResponseEntity rateDesigners(@PathVariable Integer designerId,@Valid @RequestBody Rating rating) {
        ratingService.rateDesigner(designerId, rating);
        return ResponseEntity.status(200).body("Rating Added successfully");
    }
}
