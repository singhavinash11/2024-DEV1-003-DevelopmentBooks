package com.assignment.bookstore.model;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class OrderResponse {
    private double totalPrice;
    private Map<String, OrderDetail> evaluatedOrder;
}
