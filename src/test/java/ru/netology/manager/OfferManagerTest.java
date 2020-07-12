package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Offer;
import ru.netology.domain.OfferByTimeAscComparator;
import ru.netology.repository.OfferRepository;
import java.util.Comparator;


import static org.junit.jupiter.api.Assertions.*;


class OfferManagerTest {
    private OfferRepository repository = new OfferRepository();
    private OfferManager manager = new OfferManager(repository);

    private Offer offer2 = new Offer(1, 3000, "LED", "DME", 50);
    private Offer offer1 = new Offer(1, 1000, "BOS", "KLN", 60);
    private Offer offer3 = new Offer(1, 500, "LED", "DME", 60);
    private Offer offer4 = new Offer(1, 1000, "HKG", "KLN", 600);
    private Offer offer5 = new Offer(1, 3000, "LED", "DME", 30);
    private Offer offer6 = new Offer(1, 500, "LED", "DME", 600);


    @Test
    void shouldReturnEmptyIfNotTickets() {
        Comparator<Offer> comparator = new OfferByTimeAscComparator();
        manager = new OfferManager(repository);
        Offer[] expected = new Offer[0];
        Offer[] actual = manager.findAllSortByPrice("LED", "DME");
        assertArrayEquals(expected, actual);
    }
    @Test
    void shouldSortTicketsByPriceIfContains() {
        manager = new OfferManager(repository);
        manager.offerAdd(offer1);
        manager.offerAdd(offer2);
        manager.offerAdd(offer3);
        manager.offerAdd(offer4);
        Offer[] expected = new Offer[]{offer3, offer2};
        Offer[] actual = manager.findAllSortByPrice("LED", "DME");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldReturnOneIfContains() {
        Comparator<Offer> comparator = new OfferByTimeAscComparator();
        manager = new OfferManager(repository);
        manager.offerAdd(offer2);
        manager.offerAdd(offer4);
        Offer[] expected = new Offer[]{offer2};
        Offer[] actual = manager.findAllSortByTime("LED", "DME", comparator);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldBeEmptyIfNotContain() {
        Comparator<Offer> comparator = new OfferByTimeAscComparator();
        manager = new OfferManager(repository);
        manager.offerAdd(offer2);
        manager.offerAdd(offer4);
        Offer[] expected = new Offer[]{offer2};
        Offer[] actual = manager.findAllSortByPrice("LED", "DME");
        assertArrayEquals(expected, actual);
    }
    @Test
    void shouldSortTicketsByTimeIfContain() {
        Comparator<Offer> comparator = new OfferByTimeAscComparator();
        manager = new OfferManager(repository);
        manager.offerAdd(offer1);
        manager.offerAdd(offer2);
        manager.offerAdd(offer3);
        manager.offerAdd(offer4);
        manager.offerAdd(offer5);
        manager.offerAdd(offer6);
        Offer[] expected = new Offer[]{offer5, offer2, offer3, offer6};
        Offer[] actual = manager.findAllSortByTime("LED", "DME", comparator);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldBeOneIfContain() {
        Comparator<Offer> comparator = new OfferByTimeAscComparator();
        manager = new OfferManager(repository);
        manager.offerAdd(offer1);
        manager.offerAdd(offer4);
        Offer[] expected = new Offer[0];
        Offer[] actual = manager.findAllSortByTime("LED", "DME", comparator);
        assertArrayEquals(expected, actual);
    }

}