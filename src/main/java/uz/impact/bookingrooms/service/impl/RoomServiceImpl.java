package uz.impact.bookingrooms.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.impact.bookingrooms.dto.*;
import uz.impact.bookingrooms.entity.Booking;
import uz.impact.bookingrooms.entity.Resident;
import uz.impact.bookingrooms.entity.Room;
import uz.impact.bookingrooms.exception.DataNotMatchesException;
import uz.impact.bookingrooms.exception.NoResourceFoundException;
import uz.impact.bookingrooms.repository.BookingRepository;
import uz.impact.bookingrooms.repository.ResidentRepository;
import uz.impact.bookingrooms.repository.RoomRepository;
import uz.impact.bookingrooms.service.RoomService;
import uz.impact.bookingrooms.service.mapper.BookingMapper;
import uz.impact.bookingrooms.service.mapper.ResidentMapper;
import uz.impact.bookingrooms.service.mapper.RoomMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;
    private final ResidentRepository residentRepository;
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
    public ResponseEntity<List<FreeTimeDto>> getAvailableTime(Long id, Optional<LocalDate> date) {

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
                .filter(booking -> booking.getStart().toLocalDate().isEqual(localDate))
                .forEach(booking -> {
                    freeTimes.add(booking.getStart());
                    freeTimes.add(booking.getEnd());
                });

         freeTimes.add(endOfDay);

         List<FreeTimeDto> results = new ArrayList<>();

         for (int i = 0; i < freeTimes.size(); i+=2) {
             if(!freeTimes.get(i).isEqual(freeTimes.get(i+1)))
                results.add(new FreeTimeDto(freeTimes.get(i),freeTimes.get(i+1)));
        }
        return ResponseEntity.ok(results);
    }

    @Override
    public ResponseEntity<ResponseDto> bookRoom(Long id, BookingDto book) {

        if(book.getStart().isAfter(book.getEnd())) throw new DataNotMatchesException("Siz  kiritgan  boshlanish va  tugash vaqtlari  mos kelmadi");

        Room room = roomRepository.findById(id).orElseThrow(() -> new NoResourceFoundException("xona topilmadi"));

        String residentName = book.getResidentDto().getName();

        Resident resident = residentRepository.findByName(residentName).isEmpty()
                ?residentRepository.save(ResidentMapper.toEntity(book.getResidentDto()))
                :residentRepository.findByName(residentName).get();

        List<Booking> bookings = bookingRepository.findByRoomIdOrderByStart(room.getId());
        List<Booking> bookingTimes = bookings
                .stream()
                .filter(booking -> booking.getStart().toLocalDate().isEqual(book.getStart().toLocalDate()))
                .toList();


        if(bookingTimes.isEmpty()) {
            bookingRepository.save(BookingMapper.toEntity(book,resident,room));
            return ResponseEntity.status(201).body(ResponseDto.builder().message("xona muvaffaqiyatli band qilindi").build());
        }

        for (Booking bookTime:bookingTimes) {
            if(book.getStart().isEqual(bookTime.getStart()) && book.getEnd().isEqual(bookTime.getEnd()))
                throw new DataNotMatchesException("uzr, siz tanlagan vaqtda xona band");
            if(
                    (book.getStart().isAfter(bookTime.getStart()) && book.getStart().isBefore(bookTime.getEnd())) ||
                            (book.getEnd().isAfter(bookTime.getStart()) && book.getEnd().isBefore(bookTime.getEnd()))
            ) throw  new DataNotMatchesException("uzr, siz tanlagan vaqtda xona band");
            if(
                    (book.getStart().isBefore(bookTime.getStart()) && book.getEnd().isAfter(bookTime.getStart())) ||
                            (book.getStart().isBefore(bookTime.getEnd()) && book.getEnd().isAfter(bookTime.getEnd()))
            ) throw  new DataNotMatchesException("uzr, siz tanlagan vaqtda xona band");
        }

        bookingRepository.save(BookingMapper.toEntity(book,resident,room));
        return ResponseEntity.status(201).body(ResponseDto.builder().message("xona muvaffaqiyatli band qilindi").build());
    }
}
