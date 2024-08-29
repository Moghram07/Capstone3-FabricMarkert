package com.example.capstone_3.Controller;

import com.example.capstone_3.DTO.ShippingDTO;
import com.example.capstone_3.Model.Shipping;
import com.example.capstone_3.Service.ShippingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

//All made by Omar Alshehri
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/shipping")
public class ShippingController {
    private final ShippingService shippingService;

    // Get all shippings
    @GetMapping("/get")
    public ResponseEntity<List<Shipping>> getAllShippings() {
        List<Shipping> shippings = shippingService.getAllShipping();
        return ResponseEntity.ok(shippings);
    }

    // Add a new shipping
    @PostMapping("/add")
    public ResponseEntity<String> addShipping(@RequestBody ShippingDTO shippingDTO) {
        shippingService.addShipping(shippingDTO);
        return ResponseEntity.ok("Shipping added successfully");
    }

    // Update an existing shipping
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateShipping(@PathVariable Integer id, @RequestBody ShippingDTO shippingDTO) {
        shippingDTO.setOrderId(id); // Assuming orderId is used as the shipping ID
        shippingService.updateShipping(shippingDTO);
        return ResponseEntity.ok("Shipping updated successfully");
    }

    // Delete a shipping by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteShipping(@PathVariable Integer id) {
        shippingService.deleteShipping(id);
        return ResponseEntity.ok("Shipping deleted successfully");
    }

    // Get shippings by status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Shipping>> getShippingsByStatus(@PathVariable String status) {
        List<Shipping> shippings = shippingService.getShippingsByStatus(status);
        return ResponseEntity.ok(shippings);
    }

    // Return a shipping (set status to "Returned")
    @PutMapping("/return/{id}")
    public ResponseEntity<String> returnShipping(@PathVariable Integer id) {
        shippingService.returnShipping(id);
        return ResponseEntity.ok("Shipping returned successfully");
    }

    // Calculate estimated delivery date for a shipping
    @GetMapping("/estimateDelivery/{id}")
    public ResponseEntity<LocalDateTime> calculateEstimatedDeliveryDate(@PathVariable Integer id) {
        LocalDateTime estimatedDeliveryDate = shippingService.calculateEstimatedDeliveryDate(id);
        return ResponseEntity.ok(estimatedDeliveryDate);
    }

    // Update shipping status based on order status
    @PutMapping("/updateByOrderStatus/{orderId}")
    public ResponseEntity<String> updateShippingStatusBasedOnOrderStatus(@PathVariable Integer orderId) {
        shippingService.updateShippingStatusBasedOnOrderStatus(orderId);
        return ResponseEntity.ok("Shipping status updated based on order status");
    }

    @PutMapping("/deliver/{shippingId}")
    public ResponseEntity deliverOrder(@PathVariable Integer shippingId){
        shippingService.deliverOrder(shippingId);
        return ResponseEntity.ok("Order delivered successfully");
    }
}
