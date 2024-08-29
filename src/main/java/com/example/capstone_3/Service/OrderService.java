package com.example.capstone_3.Service;

import com.example.capstone_3.Api.ApiException;
import com.example.capstone_3.Model.Fabric;
import com.example.capstone_3.Model.Order;
import com.example.capstone_3.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
//All made by Omar Alshehri
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    // Get all orders
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Get an order by ID
    public Order getOrderById(Integer id) {
        return orderRepository.findOrderById(id);
    }

    // Add a new order
    public void addOrder(Order order) {
        orderRepository.save(order);
    }

    // Update an existing order
    public void updateOrder(Integer id, Order orderDetails) {
        Order order = orderRepository.findOrderById(id);
        if (order == null) {
            throw new ApiException("Order not found");
        }
        order.setOrderStatus(orderDetails.getOrderStatus());
        order.setTotalPrice(orderDetails.getTotalPrice());
        order.setOrderDate(orderDetails.getOrderDate());
        order.setCustom(orderDetails.getCustom());
        order.setDesigner(orderDetails.getDesigner());
        order.setFabric(orderDetails.getFabric());
        order.setMerchant(orderDetails.getMerchant());
        order.setTailor(orderDetails.getTailor());

        orderRepository.save(order);
    }

    // Delete an order by ID
    public void deleteOrder(Integer id) {
        Order order = orderRepository.findOrderById(id);
        orderRepository.delete(order);
    }

    // Get orders by status
    public List<Order> getOrdersByStatus(String status) {
        return orderRepository.findOrderByOrderStatus(status);
    }

    // Get the best-selling fabric
    public Fabric getBestSellingFabric() {
        Pageable pageable = PageRequest.of(0, 1);
        List<Fabric> fabrics = orderRepository.findBestSellingFabric(pageable);
        return fabrics.isEmpty() ? null : fabrics.get(0);
    }

}
