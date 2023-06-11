package uz.impact.bookingrooms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.impact.bookingrooms.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room,Long> {
}
