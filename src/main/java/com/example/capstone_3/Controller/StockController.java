package com.example.capstone_3.Controller;

import com.example.capstone_3.DTO.StockDTO;
import com.example.capstone_3.Model.Stock;
import com.example.capstone_3.Service.StockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//All made by Omar Alshehri
@RestController
@RequestMapping("/api/v1/stock")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @GetMapping("/get")
    public ResponseEntity getAllstock() {
        return ResponseEntity.ok(stockService.getAllStock());
    }

    @PostMapping("/add")
    public ResponseEntity addStock(@RequestBody @Valid StockDTO stockDTO) {
        stockService.addStock(stockDTO);
        return ResponseEntity.status(200).body("Stock added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateStock(@PathVariable Integer id, @RequestBody @Valid Stock stock) {
        stockService.updateStock(id, stock);
        return ResponseEntity.status(200).body("Stock updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStock(@PathVariable Integer id) {
        stockService.deleteStock(id);
        return ResponseEntity.status(200).body("Stock deleted");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stock> getStockById(@PathVariable Integer id) {
        return ResponseEntity.ok(stockService.getStockById(id));
    }

    @GetMapping("/fabric/{fabricId}")
    public ResponseEntity getStockByFabricId(@PathVariable Integer fabricId) {
        return ResponseEntity.ok(stockService.getStockByFabricId(fabricId));
    }

    @GetMapping("/merchant/{merchantId}")
    public ResponseEntity getStockByMerchantId(@PathVariable Integer merchantId) {
        return ResponseEntity.ok(stockService.getStockByMerchantId(merchantId));
    }

    @GetMapping("/quantity/{quantity}")
    public ResponseEntity getStockByQuantity(@PathVariable int quantity) {
        return ResponseEntity.ok(stockService.getStockByQuantity(quantity));
    }
}
