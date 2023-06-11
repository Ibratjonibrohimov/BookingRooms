package uz.impact.bookingrooms.service;

import org.springframework.http.ResponseEntity;
import uz.impact.bookingrooms.dto.RoomDto;
import uz.impact.bookingrooms.dto.RoomResultsDto;

import java.util.List;
import java.util.Optional;

public interface RoomService {
    ResponseEntity<RoomResultsDto> getAllRooms(Optional<String> search, Optional<String> type, Optional<Integer> page, Optional<Integer> pageSize);
}
