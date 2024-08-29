package com.example.capstone_3.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderHistoryDTO {
    private Integer orderId;
    private String orderStatus;
    private double totalPrice;
    private LocalDateTime orderDate;
    private LocalDateTime deliveryDate;
    private String fabricName;
    private String designerName;
    private String tailorName;
    private String merchantName;
}
