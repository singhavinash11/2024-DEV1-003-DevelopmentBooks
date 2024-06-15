package com.assignment.bookstore.service;

import com.assignment.bookstore.model.OrderDetail;
import com.assignment.bookstore.model.OrderResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BookStoreService {
    private static final Map<String, String> BOOKS_CATALOG = Map.of(
            "book1", "Clean Code (Robert Martin, 2008)",
            "book2", "The Clean Coder (Robert Martin, 2011)",
            "book3", "Clean Architecture (Robert Martin, 2017)",
            "book4", "Test Driven Development by Example (Kent Beck, 2003)",
            "book5", "Working Effectively With Legacy Code (Michael C. Feathers, 2004)"
    );
    private static final double PRICE_PER_BOOK = 50;
    private static final double[] DISCOUNTS = {0, 0.05, 0.10, 0.20, 0.25};

    public OrderResponse calculatePrice(Map<String, OrderDetail> order) {
        Map<String, OrderDetail> validBooks = order.entrySet()
                .stream()
                .filter(entry -> BOOKS_CATALOG.containsKey(entry.getKey()) && entry.getValue().getQuantity() > 0)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        List<Integer> quantities = validBooks.values()
                .stream()
                .map(OrderDetail::getQuantity)
                .toList();

        double calculatedBestPrice = calculateBestPrice(quantities);
        return OrderResponse.builder()
                .totalPrice(calculatedBestPrice)
                .evaluatedOrder(validBooks)
                .build();
    }

    private double calculateBestPrice(List<Integer> quantities) {
        double bestPrice = Double.MAX_VALUE;

        for (int setSize = 1; setSize <= quantities.size(); setSize++) {
            double price = 0;
            List<Integer> remainingQuantities = new ArrayList<>(quantities);

            while (remainingQuantities.stream().anyMatch(currentElement -> currentElement > 0)) {
                int differentBooks = 0;

                for (int i = 0; i < remainingQuantities.size(); i++) {
                    if (remainingQuantities.get(i) > 0 && differentBooks < setSize) {
                        remainingQuantities.set(i, remainingQuantities.get(i) - 1);
                        differentBooks++;
                    }
                }
                price += differentBooks * PRICE_PER_BOOK * (1 - DISCOUNTS[differentBooks - 1]);
            }
            bestPrice = Math.min(bestPrice, price);
        }
        return bestPrice;
    }
}
