package uz.impact.bookingrooms.extension;

import uz.impact.bookingrooms.entity.Room;

import java.util.List;
import java.util.Optional;

public interface RoomRepositoryExtension {
    List<Room> getAllRooms(Optional<String> search, Optional<String> type, Optional<Integer> page, Optional<Integer> pageSize);
}
