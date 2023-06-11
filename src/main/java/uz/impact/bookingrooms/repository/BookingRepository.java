package uz.impact.bookingrooms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.impact.bookingrooms.entity.Booking;

public interface BookingRepository  extends JpaRepository<Booking,Long> {
}
