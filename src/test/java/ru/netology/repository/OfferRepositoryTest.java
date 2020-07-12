package ru.netology.repository;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Offer;
import ru.netology.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class OfferRepositoryTest {

    private OfferRepository repository = new OfferRepository();
    private Offer offer1 = new Offer(1, 1000, "BOS", "KLN", 60);
    private Offer offer2 = new Offer(2, 3000, "LED", "DME", 50);

    @Test
    void shouldSave() {
        repository.save(offer1);
        Offer[] expected = new Offer[]{offer1};
        Offer[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldReturnEmpty() {
        Offer[] expected = new Offer[]{};
        Offer[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldReturn() {
        repository.save(offer1);
        repository.save(offer2);
        Offer[] expected = new Offer[]{offer1, offer2};
        Offer[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindIfExists() {
        repository.save(offer1);
        repository.save(offer2);
        int idToFind = 2;
        repository.findById(idToFind);
        Offer expected = offer2;
        Offer actual = repository.findById(idToFind);
        assertEquals(expected, actual);
    }

    @Test
    void shouldRemoveIfExists() {
        int idToRemove = 1;
        repository.save(offer1);
        repository.save(offer2);
        repository.removeById(idToRemove);
        Offer[] actual = repository.findAll();
        Offer[] expected = new Offer[]{offer2};
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotRemoveIfNotExists() {
        int idToRemove = 4;
        repository.save(offer1);
        repository.save(offer2);
        assertThrows(NotFoundException.class, () -> repository.removeById(idToRemove));
    }

    @Test
    void shouldReturnNullIfNotExists() {
        repository.save(offer1);
        repository.save(offer2);
        int idToFind = 5;
        repository.findById(idToFind);
        Offer expected = null;
        Offer actual = repository.findById(idToFind);
        assertEquals(expected, actual);
    }


}