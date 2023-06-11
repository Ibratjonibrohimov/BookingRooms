package uz.impact.bookingrooms.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.impact.bookingrooms.dto.ErrorDto;
import uz.impact.bookingrooms.exception.NoResourceFoundException;

@RestControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDto noResourceHandler(NoResourceFoundException exception){
        return ErrorDto.builder().error(exception.getMessage()).build();
    }
}
