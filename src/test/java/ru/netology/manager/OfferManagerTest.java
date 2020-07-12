package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Offer;
import ru.netology.repository.OfferRepository;

import static org.junit.jupiter.api.Assertions.*;

class OfferManagerTest {
    OfferRepository repository = new OfferRepository();
    OfferManager manager = new OfferManager(repository);

    Offer offer1 = new Offer(1, 1000, "LED", "DME", 60);
    Offer offer2 = new Offer(1, 3000, "DME", "LED", 60);
    Offer offer3 = new Offer(1, 500, "LED", "DME", 60);
    Offer offer4 = new Offer(1, 500, "DME", "KLN", 60);

    @Test
    void shouldReturnEmptyIfNotTickets() {
        manager = new OfferManager(repository);
        Offer[] expected = new Offer[0];
        Offer[] actual = manager.findAll("LED", "DME");
        assertArrayEquals(expected, actual);
    }
    @Test
    void shouldSortTicketsIfContain() {
        manager = new OfferManager(repository);
        manager.offerAdd(offer1);
        manager.offerAdd(offer2);
        manager.offerAdd(offer3);
        manager.offerAdd(offer4);
        Offer[] expected = new Offer[]{offer3, offer1};
        Offer[] actual = manager.findAll("LED", "DME");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldReturnEmptyIfNotContain() {
        manager = new OfferManager(repository);
        manager.offerAdd(offer2);
        manager.offerAdd(offer4);
        Offer[] expected = new Offer[0];
        Offer[] actual = manager.findAll("LED", "DME");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldReturnTicketIfContain() {
        manager = new OfferManager(repository);
        manager.offerAdd(offer1);
        manager.offerAdd(offer2);
        Offer[] expected = new Offer[]{offer1};
        Offer[] actual = manager.findAll("LED", "DME");
        assertArrayEquals(expected, actual);
    }

}