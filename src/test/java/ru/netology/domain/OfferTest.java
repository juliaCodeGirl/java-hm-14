package ru.netology.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class OfferTest {
    Offer offer1 = new Offer(1, 1000, "LED", "DME", 60);
    Offer offer2 = new Offer(1, 3000, "LED", "DME", 60);
    Offer offer3 = new Offer(1, 500, "LED", "DME", 60);

    @Test
    void shouldSortByPrice() {
        Offer[] expected = new Offer[]{offer3, offer1, offer2};
        Offer[] actual = new Offer[]{offer1, offer2, offer3};

        Arrays.sort(actual);

        assertArrayEquals(expected, actual);
    }
}