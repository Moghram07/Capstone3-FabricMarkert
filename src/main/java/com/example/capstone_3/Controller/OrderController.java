package com.example.capstone_3.Controller;

import com.example.capstone_3.Model.Fabric;
import com.example.capstone_3.Model.Order;
import com.example.capstone_3.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//All made by Omar Alshehri
@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // Get all orders
    @GetMapping("/get")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    // Get an order by ID
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Integer id) {
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    // Add a new order
    @PostMapping("/add")
    public ResponseEntity<String> addOrder(@RequestBody Order order) {
        orderService.addOrder(order);
        return ResponseEntity.ok("Order added successfully");
    }

    // Update an existing order
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateOrder(@PathVariable Integer id, @RequestBody Order orderDetails) {
        orderService.updateOrder(id, orderDetails);
        return ResponseEntity.ok("Order updated successfully");
    }

    // Delete an order by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Integer id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok("Order deleted successfully");
    }

    // Get orders by status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Order>> getOrdersByStatus(@PathVariable String status) {
        List<Order> orders = orderService.getOrdersByStatus(status);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/bestSelling")
    public ResponseEntity getBestSellingFabric() {
        Fabric bestSellingFabric = orderService.getBestSellingFabric();
        if (bestSellingFabric == null) {
            return ResponseEntity.ok("No best selling fabric found");
        }
        return ResponseEntity.ok(bestSellingFabric);
    }
}