package com.assignment.bookstore.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class BookStoreServiceTest {

    @InjectMocks
    private BookStoreService bookStoreService;

    @Test
    @DisplayName("ShouldCalculateForSingleTypeOfBook")
    void shouldCalculateForSingleTypeOfBook() {
        //Given
        var quantity = 2;

        //Then
        assertEquals(100, bookStoreService.calculatePrice(quantity));
    }
}