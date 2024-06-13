package com.assignment.bookstore.controller;

import com.assignment.bookstore.model.OrderDetail;
import com.assignment.bookstore.model.OrderResponse;
import com.assignment.bookstore.service.BookStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/bookstore")
@RequiredArgsConstructor
public class BookStoreController {
    private final BookStoreService bookStoreService;

    @PostMapping("/total-price")
    public OrderResponse calculatePrice(@RequestBody Map<String, OrderDetail> order) {
        return bookStoreService.calculatePrice(order);
    }
}
