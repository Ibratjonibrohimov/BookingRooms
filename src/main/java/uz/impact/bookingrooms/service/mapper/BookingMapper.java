package uz.impact.bookingrooms.service.mapper;

import uz.impact.bookingrooms.dto.BookingDto;
import uz.impact.bookingrooms.entity.Booking;
import uz.impact.bookingrooms.entity.Resident;
import uz.impact.bookingrooms.entity.Room;

public class BookingMapper {
    public static BookingDto toDto(Booking booking){
        return new BookingDto(
                ResidentMapper.toDto(booking.getResident()),
                booking.getStart(),
                booking.getEnd()
        );
    }

    public static Booking toEntity(BookingDto book, Resident resident, Room room) {
        return new Booking(resident,book.getStart(),book.getEnd(),room);
    }
}
