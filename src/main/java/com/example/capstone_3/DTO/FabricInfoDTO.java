package com.example.capstone_3.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class FabricInfoDTO {
    private Integer orderId;
    private Integer merchantId;
    private String orderStatus;
    private String fabricName;
    private double fabricPrice;
}