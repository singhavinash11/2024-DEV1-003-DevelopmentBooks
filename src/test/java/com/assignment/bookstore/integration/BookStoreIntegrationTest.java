package com.assignment.bookstore.integration;

import com.assignment.bookstore.model.OrderDetail;
import com.assignment.bookstore.model.OrderResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestClient;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class BookStoreIntegrationTest {

    @LocalServerPort
    private int port;

    private static RestClient restClient;

    private String baseUrl = "http://localhost";

    @BeforeAll
    static void beforeAll() {
        restClient = RestClient.create();
    }

    @BeforeEach
    void setUp() {
        baseUrl = baseUrl + ":" + port + "/bookstore";
    }

    @Test
    @DisplayName("ShouldCalculateForRandomCombination")
    void shouldCalculateForRandomCombination() {
        //Given
        Map<String, OrderDetail> orderDetails = new HashMap<>();
        orderDetails.put("book1", new OrderDetail(2));
        orderDetails.put("book2", new OrderDetail(2));
        orderDetails.put("book3", new OrderDetail(2));
        orderDetails.put("book4", new OrderDetail(1));
        orderDetails.put("book5", new OrderDetail(1));

        //And
        OrderResponse orderResponse = restClient.post()
                .uri(baseUrl + "/total-price")
                .body(orderDetails)
                .retrieve()
                .body(OrderResponse.class);

        //Then
        assertNotNull(orderResponse);
        assertEquals(320, orderResponse.getTotalPrice());
    }
}
