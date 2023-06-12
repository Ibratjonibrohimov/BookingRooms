package uz.impact.bookingrooms.service;

import org.springframework.http.ResponseEntity;
import uz.impact.bookingrooms.dto.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RoomService {
    ResponseEntity<RoomResultsDto> getAllRooms(Optional<String> search, Optional<String> type, Optional<Integer> page, Optional<Integer> pageSize);

    ResponseEntity<RoomDto> getById(Long id);

    ResponseEntity<List<FreeTimeDto>> getAvailableTime(Long id, Optional<LocalDate> date);

    ResponseEntity<ResponseDto> bookRoom(Long id, BookingDto book);
}
