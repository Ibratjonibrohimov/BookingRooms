package uz.impact.bookingrooms.dto;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.impact.bookingrooms.entity.Resident;
import uz.impact.bookingrooms.entity.Room;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class BookingDto {
    private ResidentDto residentDto;

    private LocalDateTime start;
    private LocalDateTime end;
}
