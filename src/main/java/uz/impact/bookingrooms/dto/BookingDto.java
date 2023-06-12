package uz.impact.bookingrooms.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty(value = "resident")
    private ResidentDto residentDto;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime start;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime end;
}
