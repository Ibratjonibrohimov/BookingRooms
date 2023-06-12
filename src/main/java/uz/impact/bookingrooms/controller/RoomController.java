package uz.impact.bookingrooms.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.impact.bookingrooms.dto.*;
import uz.impact.bookingrooms.entity.Booking;
import uz.impact.bookingrooms.repository.BookingRepository;
import uz.impact.bookingrooms.service.RoomService;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;
    @GetMapping()
    public ResponseEntity<RoomResultsDto> getAllRooms(@RequestParam Optional<String> search,
                                                      @RequestParam Optional<String> type,
                                                      @RequestParam Optional<Integer> page,
                                                      @RequestParam(value = "page_size") Optional<Integer> pageSize)
    {
        return roomService.getAllRooms(search,type,page,pageSize);
    }
    @GetMapping("/{id}")
    public ResponseEntity<RoomDto> getById(@PathVariable Long id){
        return roomService.getById(id);
    }

    @GetMapping("/{id}/availability")
    public ResponseEntity<List<FreeTimeDto>> getAvailableTim(@PathVariable Long id,
                                                             @RequestParam Optional<LocalDate> date)
    {
        return roomService.getAvailableTime(id,date);
    }
    @PostMapping("/{id}/book")
    public ResponseEntity<ResponseDto> bookRoom(@PathVariable Long id, @RequestBody BookingDto book){
        return roomService.bookRoom(id,book);
    }
}
