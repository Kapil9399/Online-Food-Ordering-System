package com.food.models;

import com.food.dto.RestaurantDto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    void testAllArgsConstructor() {

        List<Order> orders = new ArrayList<>();
        List<RestaurantDto> favorites = new ArrayList<>();
        List<Address> addresses = new ArrayList<>();

        User user = new User(
                1L,
                "Kapil Rajput",
                "kapil@gmail.com",
                "12345",
                USER_ROLE.ROLE_CUSTOMER,
                orders,
                favorites,
                addresses
        );

        assertEquals(1L, user.getId());
        assertEquals("Kapil Rajput", user.getFullName());
        assertEquals("kapil@gmail.com", user.getEmail());
        assertEquals("12345", user.getPassword());
        assertEquals(USER_ROLE.ROLE_CUSTOMER, user.getRole());
    }

    // Getter Setter Testing
    @Test
    void testGetterSetter() {

        User user = new User();

        user.setId(2L);
        user.setFullName("Kapil");
        user.setEmail("kapil@test.com");
        user.setPassword("pass123");

        assertEquals(2L, user.getId());
        assertEquals("Kapil", user.getFullName());
        assertEquals("kapil@test.com", user.getEmail());
        assertEquals("pass123", user.getPassword());
    }

    // Default Role Testing
    @Test
    void testDefaultRole() {

        User user = new User();

        assertEquals(USER_ROLE.ROLE_CUSTOMER, user.getRole());
    }

    // List Initialization Testing
    @Test
    void testListInitialization() {

        User user = new User();

        assertNotNull(user.getOrders());
        assertNotNull(user.getFavorites());
        assertNotNull(user.getAddresses());

        assertTrue(user.getOrders().isEmpty());
        assertTrue(user.getFavorites().isEmpty());
        assertTrue(user.getAddresses().isEmpty());
    }



}