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

    @Test
    void testGetterSetter()
    {
     Address address = new Address();

     address.setId(1L);
     address.setStreetAddress("Nanda Nagar");
     address.setCity("indore");
     address.setState("Madhya Pradesh");
     address.setPincode("452010");
     address.setCountry("India");

     assertEquals(1L, address.getId());
     assertEquals("Nanda Nagar", address.getStreetAddress());
     assertEquals("indore", address.getCity());
     assertEquals("Madhya Pradesh", address.getState());
     assertEquals("452010", address.getPincode());
     assertEquals("India", address.getCountry());
    }
}
