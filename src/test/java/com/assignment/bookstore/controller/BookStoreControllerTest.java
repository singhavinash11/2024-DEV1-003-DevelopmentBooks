package com.assignment.bookstore.controller;

import com.assignment.bookstore.model.OrderDetail;
import com.assignment.bookstore.model.OrderResponse;
import com.assignment.bookstore.service.BookStoreService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookStoreController.class)
class BookStoreControllerTest {

    @MockBean
    private BookStoreService bookStoreService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("ShouldCalculateForRandomCombination")
    void shouldCalculateForRandomCombination() throws Exception {
        //Given
        Map<String, OrderDetail> orderDetails = new HashMap<>();
        orderDetails.put("book1", new OrderDetail(2));
        orderDetails.put("book2", new OrderDetail(2));
        orderDetails.put("book3", new OrderDetail(2));
        orderDetails.put("book4", new OrderDetail(1));
        orderDetails.put("book5", new OrderDetail(1));

        OrderResponse response = OrderResponse.builder()
                .totalPrice(320)
                .evaluatedOrder(orderDetails)
                .build();

        //When
        when(bookStoreService.calculatePrice(orderDetails)).thenReturn(response);

        //Then
        mockMvc.perform(post("/bookstore/total-price")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderDetails)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }
}