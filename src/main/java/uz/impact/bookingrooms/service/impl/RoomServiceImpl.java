package uz.impact.bookingrooms.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.impact.bookingrooms.dto.FreeTimeDto;
import uz.impact.bookingrooms.dto.RoomDto;
import uz.impact.bookingrooms.dto.RoomResultsDto;
import uz.impact.bookingrooms.entity.Booking;
import uz.impact.bookingrooms.entity.Room;
import uz.impact.bookingrooms.exception.NoResourceFoundException;
import uz.impact.bookingrooms.repository.BookingRepository;
import uz.impact.bookingrooms.repository.RoomRepository;
import uz.impact.bookingrooms.service.RoomService;
import uz.impact.bookingrooms.service.mapper.RoomMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;
    @Override
    public ResponseEntity<RoomResultsDto> getAllRooms(Optional<String> search, Optional<String> type, Optional<Integer> page, Optional<Integer> pageSize) {
        List<Room> results = roomRepository.getAllRooms(search,type,page,pageSize);
        var roomResultsDto = new RoomResultsDto(
                page.orElse(null),
                results.size(),
                pageSize.orElse(null),
                results.stream().map(RoomMapper::toDto).collect(Collectors.toList())
        );
        return ResponseEntity.ok(roomResultsDto);
    }

    @Override
    public ResponseEntity<RoomDto> getById(Long id) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new NoResourceFoundException("topilmadi"));
        return ResponseEntity.ok(RoomMapper.toDto(room));
    }

    @Override
    public ResponseEntity<List<FreeTimeDto>> getAvialableTime(Long id, Optional<LocalDate> date) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new NoResourceFoundException("xona topilmadi"));
        LocalDate localDate = date.orElse(LocalDate.now());
        List<Booking> bookings = bookingRepository.findByRoomIdOrderByStart(room.getId());

        LocalDateTime startOfDay = LocalDateTime.of(localDate, LocalTime.of(0,0,0));
        LocalDateTime endOfDay = LocalDateTime.of(localDate,LocalTime.of(23,59,59));
        if(bookings.isEmpty()) return ResponseEntity.ok(List.of(new FreeTimeDto(startOfDay,endOfDay)));

        List<LocalDateTime> freeTimes = new ArrayList<>();
        freeTimes.add(startOfDay);

         bookings
                .stream()
                .filter(booking -> booking.getStart().toLocalDate() == localDate)
                .forEach(booking -> {
                    freeTimes.add(booking.getStart());
                    freeTimes.add(booking.getEnd());
                });

         List<FreeTimeDto> results = new ArrayList<>();
        for (int i = 0; i < freeTimes.size(); i+=2) {
            results.add(new FreeTimeDto(freeTimes.get(i),freeTimes.get(i+1)));
        }
        return ResponseEntity.ok(results);
    }
}
