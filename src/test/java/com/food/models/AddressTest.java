package com.food.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddressTest {

    @Test
    void testAllArgsConstructor()
    {
        Address address = new Address(
                1L,
                "vijay nagar",
                "indore",
                "Madhya Pradesh",
                "452010",
                "India"
        );
        assertEquals(1L, address.getId());
        assertEquals("vijay nagar", address.getStreetAddress());
        assertEquals("indore", address.getCity());
        assertEquals("Madhya Pradesh", address.getState());
        assertEquals("452010", address.getPincode());
        assertEquals("India", address.getCountry());
    }
}
