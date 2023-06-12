package uz.impact.bookingrooms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.impact.bookingrooms.entity.Booking;
import uz.impact.bookingrooms.extension.BookingRepositoryExtension;

import java.util.*;
@Repository
public interface BookingRepository  extends JpaRepository<Booking,Long>, BookingRepositoryExtension {
    List<Booking> findByRoomIdOrderByStart(Long id);
}
