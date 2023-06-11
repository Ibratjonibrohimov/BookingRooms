package uz.impact.bookingrooms.extension.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import uz.impact.bookingrooms.entity.Booking;
import uz.impact.bookingrooms.entity.Room;
import uz.impact.bookingrooms.extension.BookingRepositoryExtension;

import java.time.LocalDate;
import java.util.List;
@RequiredArgsConstructor
public class BookingRepositoryExtensionImpl implements BookingRepositoryExtension {
    private final EntityManager entityManager;
    @Override
    public List<Booking> getAvailableTime(Room room) {

        TypedQuery<Booking> query = entityManager.createQuery("select b from Booking b where b.room = room ",Booking.class);
        return query.getResultList();
    }
}
