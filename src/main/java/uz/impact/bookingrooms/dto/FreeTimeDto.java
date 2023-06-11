package uz.impact.bookingrooms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FreeTimeDto {
    private LocalDateTime start;
    private LocalDateTime end;

}
