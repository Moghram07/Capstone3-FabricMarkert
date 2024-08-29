package com.example.capstone_3.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DesignerInfoDTO {
    private Integer orderId;
    private String orderStatus;
    private double designerPrice;
}
