package com.assignment.bookstore.service;

import com.assignment.bookstore.model.OrderDetail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class BookStoreServiceTest {

    @InjectMocks
    private BookStoreService bookStoreService;

    Map<String, OrderDetail> orderDetails;

    @BeforeEach
    void setUp() {
        orderDetails = new HashMap<>();
    }

    @Test
    @DisplayName("ShouldCalculateForSingleTypeOfBook")
    void shouldCalculateForSingleTypeOfBook() {
        //Given
        orderDetails.put("book1", new OrderDetail(2));

        //Then
        assertEquals(100, bookStoreService.calculatePrice(orderDetails));
    }

    @Test
    @DisplayName("ShouldCalculateForTwoDifferentTypeOfBook")
    void shouldCalculateForTwoDifferentTypeOfBook() {
        //Given
        orderDetails.put("book1", new OrderDetail(1));
        orderDetails.put("book2", new OrderDetail(1));

        //Then
        assertEquals(95, bookStoreService.calculatePrice(orderDetails));
    }

    @Test
    @DisplayName("ShouldCalculateForThreeDifferentTypeOfBook")
    void shouldCalculateForThreeDifferentTypeOfBook() {
        //Given
        orderDetails.put("book1", new OrderDetail(1));
        orderDetails.put("book2", new OrderDetail(1));
        orderDetails.put("book3", new OrderDetail(1));

        //Then
        assertEquals(135, bookStoreService.calculatePrice(orderDetails));
    }
}