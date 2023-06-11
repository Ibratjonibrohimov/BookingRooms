package uz.impact.bookingrooms.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.impact.bookingrooms.dto.RoomDto;
import uz.impact.bookingrooms.dto.RoomResultsDto;
import uz.impact.bookingrooms.service.RoomService;

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
}
