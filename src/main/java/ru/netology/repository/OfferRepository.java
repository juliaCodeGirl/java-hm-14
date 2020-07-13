package ru.netology.repository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ru.netology.domain.Offer;
import ru.netology.exception.NotFoundException;

@NoArgsConstructor
@AllArgsConstructor
public class OfferRepository {
    private Offer[] tickets = new Offer[0];

    public void save(Offer item) {
        int length = tickets.length + 1;
        Offer[] tmp = new Offer[length];
        System.arraycopy(tickets, 0, tmp, 0, tickets.length);
        int lastIndex = tmp.length - 1;
        tmp[lastIndex] = item;
        tickets = tmp;
    }

    public Offer[] findAll() {
        return tickets;
    }

    public Offer findById(int id) {
        for (Offer offer : tickets) {
            if (offer.getId() == id) {
                return offer;
            }
        }
        return null;
    }

    public void removeById(int id) {
        if (findById(id) == null) {
            throw new NotFoundException("Element with id: " + id + " not found");
        }
        int length = tickets.length - 1;
        Offer[] tmp = new Offer[length];
        int index = 0;
        for (Offer item : tickets) {
            if (item.getId() != id) {
                tmp[index] = item;
                index++;
            }
        }
        tickets = tmp;
    }
}
