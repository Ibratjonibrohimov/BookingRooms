package uz.impact.bookingrooms.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.impact.bookingrooms.dto.RoomDto;
import uz.impact.bookingrooms.dto.RoomResultsDto;
import uz.impact.bookingrooms.entity.Room;
import uz.impact.bookingrooms.repository.RoomRepository;
import uz.impact.bookingrooms.service.RoomService;
import uz.impact.bookingrooms.service.mapper.RoomMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
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
}
