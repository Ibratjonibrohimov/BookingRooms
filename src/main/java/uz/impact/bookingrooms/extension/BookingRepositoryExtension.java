package uz.impact.bookingrooms.extension;

import uz.impact.bookingrooms.entity.Booking;
import uz.impact.bookingrooms.entity.Room;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepositoryExtension {
    List<Booking> getAvailableTime(Room room);
}
