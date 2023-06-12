package uz.impact.bookingrooms.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataNotMatchesException extends RuntimeException{
    private String message;
}
