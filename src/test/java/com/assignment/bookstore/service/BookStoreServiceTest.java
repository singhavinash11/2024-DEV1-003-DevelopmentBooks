package com.assignment.bookstore.service;

import com.assignment.bookstore.model.OrderDetail;
import com.assignment.bookstore.model.OrderResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

        //And
        OrderResponse orderResponse = bookStoreService.calculatePrice(orderDetails);

        //Then
        assertEquals(100, orderResponse.getTotalPrice());
    }

    @Test
    @DisplayName("ShouldCalculateForTwoDifferentTypeOfBook")
    void shouldCalculateForTwoDifferentTypeOfBook() {
        //Given
        orderDetails.put("book1", new OrderDetail(1));
        orderDetails.put("book2", new OrderDetail(1));

        //And
        OrderResponse orderResponse = bookStoreService.calculatePrice(orderDetails);

        //Then
        assertEquals(95, orderResponse.getTotalPrice());
    }

    @Test
    @DisplayName("ShouldCalculateForThreeDifferentTypeOfBook")
    void shouldCalculateForThreeDifferentTypeOfBook() {
        //Given
        orderDetails.put("book1", new OrderDetail(1));
        orderDetails.put("book2", new OrderDetail(1));
        orderDetails.put("book3", new OrderDetail(1));

        //And
        OrderResponse orderResponse = bookStoreService.calculatePrice(orderDetails);

        //Then
        assertEquals(135, orderResponse.getTotalPrice());
    }

    @Test
    @DisplayName("ShouldCalculateForFourDifferentTypeOfBook")
    void shouldCalculateForFourDifferentBook() {
        //Given
        orderDetails.put("book1", new OrderDetail(1));
        orderDetails.put("book2", new OrderDetail(1));
        orderDetails.put("book3", new OrderDetail(1));
        orderDetails.put("book4", new OrderDetail(1));

        //And
        OrderResponse orderResponse = bookStoreService.calculatePrice(orderDetails);

        //Then
        assertNotNull(orderResponse);
        assertEquals(160, orderResponse.getTotalPrice());
    }
}
