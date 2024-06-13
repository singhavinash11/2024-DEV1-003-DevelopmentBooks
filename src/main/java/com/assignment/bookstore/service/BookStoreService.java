package com.assignment.bookstore.service;

import org.springframework.stereotype.Service;

@Service
public class BookStoreService {
    private static final double PRICE_PER_BOOK = 50;

    public double calculatePrice(int quantity) {
        return quantity * PRICE_PER_BOOK;
    }
}
