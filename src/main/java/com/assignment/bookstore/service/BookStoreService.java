package com.assignment.bookstore.service;

import com.assignment.bookstore.model.OrderDetail;
import com.assignment.bookstore.model.OrderResponse;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BookStoreService {
    private static final Map<String, String> BOOKS_CATALOG = Map.of(
            "book1", "description 1",
            "book2", "description 2",
            "book3", "description 3",
            "book4", "description 4",
            "book5", "description 5"
    );
    private static final double PRICE_PER_BOOK = 50;
    private static final double[] DISCOUNTS = {0, 0.05, 0.10, 0.20, 0.25};

    public OrderResponse calculatePrice(Map<String, OrderDetail> order) {
        Map<String, OrderDetail> validBooks = order.entrySet()
                .stream()
                .filter(entry -> BOOKS_CATALOG.containsKey(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        int[] quantities = validBooks.values()
                .stream()
                .mapToInt(OrderDetail::getQuantity)
                .toArray();

        double calculatedPrice = calculatePrice(quantities);
        return OrderResponse.builder()
                .totalPrice(calculatedPrice)
                .evaluatedOrder(validBooks)
                .build();
    }

    private double calculatePrice(int[] quantities) {
        double total = 0;
        while (Arrays.stream(quantities).sum() > 0) {
            int differentBooks = 0;
            for (int i = 0; i < quantities.length; i++) {
                if (quantities[i] > 0) {
                    quantities[i]--;
                    differentBooks++;
                }
            }
            total += differentBooks * PRICE_PER_BOOK * (1 - DISCOUNTS[differentBooks - 1]);
        }
        return total;
    }
}
