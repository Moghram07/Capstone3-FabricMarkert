package com.example.capstone_3.Controller;

import com.example.capstone_3.DTO.FabricInfoDTO;
import com.example.capstone_3.Model.Fabric;
import com.example.capstone_3.Service.FabricService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//All made by Omar Alshehri
@RestController
@RequestMapping("/api/v1/fabric")
@RequiredArgsConstructor
public class FabricController {

    private final FabricService fabricService;

    @GetMapping("/get")
    public ResponseEntity getAllFabric() {
        return ResponseEntity.ok(fabricService.getAllFabric());
    }

    @PostMapping("/add/{merchantId}")
    public ResponseEntity addFabric(@RequestBody @Valid Fabric fabric ,@PathVariable Integer merchantId) {
        fabricService.addFabric(merchantId,fabric);
        return ResponseEntity.status(200).body("Fabric added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCustomer(@PathVariable Integer id, @RequestBody @Valid Fabric fabric) {
        fabricService.updateFabric(id, fabric);
        return ResponseEntity.status(200).body("Fabric updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCustomer(@PathVariable Integer id) {
        fabricService.deleteFabric(id);
        return ResponseEntity.status(200).body("Fabric deleted");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fabric> getFabricById(@PathVariable Integer id) {
        Fabric fabric = fabricService.getFabricById(id);
        return ResponseEntity.ok(fabric);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity getFabricByName(@PathVariable String name) {
        List<Fabric> fabrics = fabricService.getFabricByName(name);
        return ResponseEntity.ok(fabrics);
    }

    @GetMapping("/color/{color}")
    public ResponseEntity getFabricByColor(@PathVariable String color) {
        List<Fabric> fabrics = fabricService.getFabricByColor(color);
        return ResponseEntity.ok(fabrics);
    }

    @GetMapping("/pattern/{pattern}")
    public ResponseEntity getFabricByPattern(@PathVariable String pattern) {
        List<Fabric> fabrics = fabricService.getFabricByPattern(pattern);
        return ResponseEntity.ok(fabrics);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity getFabricByType(@PathVariable String type) {
        List<Fabric> fabrics = fabricService.getFabricByType(type);
        return ResponseEntity.ok(fabrics);
    }

    @GetMapping("/priceRange/{minPrice}/{maxPrice}")
    public ResponseEntity getFabricByPriceRange(@PathVariable double minPrice, @PathVariable double maxPrice) {
        List<Fabric> fabrics = fabricService.getFabricByPriceRange(minPrice, maxPrice);
        return ResponseEntity.ok(fabrics);
    }

    @GetMapping("/merchant/{merchantId}")
    public ResponseEntity getFabricByMerchant(@PathVariable Integer merchantId) {
        List<Fabric> fabrics = fabricService.getFabricByMerchant(merchantId);
        return ResponseEntity.ok(fabrics);
    }

    @PutMapping("/{fabricId}/assign/{merchantId}")
    public ResponseEntity assignMerchant(@PathVariable Integer fabricId, @PathVariable Integer merchantId) {
        fabricService.assignMerchant(fabricId, merchantId);
        return ResponseEntity.ok("Merchant assigned");
    }

    @GetMapping("/top/color")
    public ResponseEntity getTopColor() {
        return ResponseEntity.status(200).body(fabricService.topSellColor());
    }
    @GetMapping("/order-history/{fabricId}")
    public ResponseEntity getFabricOrderHistory(@PathVariable Integer fabricId) {
        List<FabricInfoDTO> fabricInfo = fabricService.getFabricOrderHistory(fabricId);
        return ResponseEntity.status(200).body(fabricInfo);
    }


}
