package com.assignment.bookstore.service;

import com.assignment.bookstore.exception.BadRequestException;
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
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        assertNotNull(orderResponse);
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
        assertNotNull(orderResponse);
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
        assertNotNull(orderResponse);
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

    @Test
    @DisplayName("ShouldCalculateForFiveDifferentBook")
    void shouldCalculateForFiveDifferentBook() {
        //Given
        orderDetails.put("book1", new OrderDetail(1));
        orderDetails.put("book2", new OrderDetail(1));
        orderDetails.put("book3", new OrderDetail(1));
        orderDetails.put("book4", new OrderDetail(1));
        orderDetails.put("book5", new OrderDetail(1));

        //And
        OrderResponse orderResponse = bookStoreService.calculatePrice(orderDetails);

        //Then
        assertNotNull(orderResponse);
        assertEquals(187.5, orderResponse.getTotalPrice());
    }

    @Test
    @DisplayName("ShouldCalculateForThreeDifferentAndOneDuplicateBook")
    void shouldCalculateForThreeDifferentAndOneDuplicateBook() {
        //Given
        orderDetails.put("book1", new OrderDetail(1));
        orderDetails.put("book2", new OrderDetail(1));
        orderDetails.put("book3", new OrderDetail(2));

        //And
        OrderResponse orderResponse = bookStoreService.calculatePrice(orderDetails);

        //Then
        assertNotNull(orderResponse);
        assertEquals(185, orderResponse.getTotalPrice());
    }

    @Test
    @DisplayName("ShouldCalculateForRandomCombination")
    void shouldCalculateForRandomCombination() {
        //Given
        orderDetails.put("book1", new OrderDetail(2));
        orderDetails.put("book2", new OrderDetail(2));
        orderDetails.put("book3", new OrderDetail(2));
        orderDetails.put("book4", new OrderDetail(1));
        orderDetails.put("book5", new OrderDetail(1));

        //And
        OrderResponse orderResponse = bookStoreService.calculatePrice(orderDetails);

        //Then
        assertNotNull(orderResponse);
        assertEquals(320, orderResponse.getTotalPrice());
    }

    @Test
    @DisplayName("shouldThrowExceptionForEmptyOrInvalidOrder")
    void shouldThrowExceptionForEmptyOrInvalidOrder() {
        //Given
        orderDetails.put("book1", new OrderDetail(0));
        orderDetails.put("book2", new OrderDetail(0));

        //Then
        assertThrows(BadRequestException.class, () -> bookStoreService.calculatePrice(orderDetails));
    }
}
