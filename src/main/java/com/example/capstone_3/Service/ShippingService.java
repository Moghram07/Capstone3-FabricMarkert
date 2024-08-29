package com.example.capstone_3.Service;

import com.example.capstone_3.Api.ApiException;
import com.example.capstone_3.DTO.ShippingDTO;
import com.example.capstone_3.Model.Order;
import com.example.capstone_3.Model.Shipping;
import com.example.capstone_3.Repository.OrderRepository;
import com.example.capstone_3.Repository.ShippingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
//All made by Omar Alshehri
@Service
@RequiredArgsConstructor
public class ShippingService {
    private final ShippingRepository shippingRepository;
    private final OrderRepository orderRepository;


    //All made by Omar Alshehri
    public List<Shipping> getAllShipping(){
        return shippingRepository.findAll();
    }

    public void addShipping(ShippingDTO shippingDTO) {
        Order o = orderRepository.findOrderById(shippingDTO.getOrderId());
        if (o == null) {
            throw new ApiException("Order not found");
        }
        Shipping shipping = new Shipping(null,shippingDTO.getShipperName(),shippingDTO.getPrice(),shippingDTO.getStatus(),o);
        shippingRepository.save(shipping);
    }

    public void updateShipping(ShippingDTO shippingDTO) {
        Shipping shipping = shippingRepository.findShippingById(shippingDTO.getOrderId());
        if (shipping == null) {
            throw new ApiException("Shipping not found");
        }
        shipping.setStatus(shippingDTO.getStatus());
        shipping.setShipperName(shippingDTO.getShipperName());
        shipping.setPrice(shippingDTO.getPrice());
        shippingRepository.save(shipping);
    }

    public void deleteShipping(Integer id){
        Shipping shipping = shippingRepository.findShippingById(id);
        if(shipping == null){
            throw new ApiException("Shipping not found");
        }
        shippingRepository.delete(shipping);
    }

    public List<Shipping> getShippingsByStatus(String status) {
        return shippingRepository.findShippingByStatus(status);
    }

    public void returnShipping(Integer shippingId) {
        Shipping shipping = shippingRepository.findShippingById(shippingId);
        if (shipping == null) {
            throw new ApiException("Shipping not found");
        }

        if ("Delivered".equalsIgnoreCase(shipping.getStatus())) {
            throw new ApiException("Cannot return a delivered shipment");
        }

        shipping.setStatus("Returned");
        shippingRepository.save(shipping);
    }

    public LocalDateTime calculateEstimatedDeliveryDate(Integer shippingId) {
        Shipping shipping = shippingRepository.findShippingById(shippingId);
        if (shipping == null) {
            throw new ApiException("Shipping not found");
        }

        if ("Shipped".equalsIgnoreCase(shipping.getStatus())) {
            return shipping.getOrder().getOrderDate().plusDays(5);
        } else if ("Out of Delivery".equalsIgnoreCase(shipping.getStatus())) {
            return shipping.getOrder().getOrderDate().plusDays(2);
        } else {
            throw new ApiException("Cannot calculate delivery date for the current shipping status");
        }
    }

    public void deliverOrder(Integer shippingId) {
        Order order = orderRepository.findOrderById(shippingId);
        if (order == null) {
            throw new ApiException("Order not found");
        }
        Shipping shipping = order.getShipping();
        order.setOrderStatus("Delivered");
        orderRepository.save(order);
    }

    public void updateShippingStatusBasedOnOrderStatus(Integer orderId) {
        Order order = orderRepository.findOrderById(orderId);
        if (order == null) {
            throw new ApiException("Order not found");
        }
        Shipping shipping = order.getShipping();

        switch (order.getOrderStatus()) {
            case "Pending":
                shipping.setStatus("initialled");
                break;
            case "Shipped":
                shipping.setStatus("Shipped");
                break;
            case "Delivered":
                shipping.setStatus("Delivered");
                break;
            case "Reject By Merchant":
            case "Reject By Tailor":
            case "Reject By Designer":
                shipping.setStatus("Returned");
                break;
            default:
                throw new ApiException("Invalid order status for updating shipping");
        }

        shippingRepository.save(shipping);
    }

    public void assignShipper(Integer shippingId,String shipperName) {
        Shipping shipping = shippingRepository.findShippingById(shippingId);
        if (shipping == null){
            throw new ApiException("Shipping not found");
        }
        shipping.setShipperName(shipperName);
        shippingRepository.save(shipping);
    }
}
