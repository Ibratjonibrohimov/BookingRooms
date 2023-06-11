package uz.impact.bookingrooms.service.mapper;

import uz.impact.bookingrooms.dto.BookingDto;
import uz.impact.bookingrooms.entity.Booking;

public class BookingMapper {
    public static BookingDto toDto(Booking booking){
        return new BookingDto(
                ResidentMapper.toDto(booking.getResident()),
                booking.getStart(),
                booking.getEnd()
        );
    }
}
