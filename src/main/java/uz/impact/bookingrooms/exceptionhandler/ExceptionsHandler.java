package uz.impact.bookingrooms.exceptionhandler;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.impact.bookingrooms.dto.ErrorDto;
import uz.impact.bookingrooms.exception.DataNotMatchesException;
import uz.impact.bookingrooms.exception.NoResourceFoundException;

@RestControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDto noResourceHandler(NoResourceFoundException exception){
        return ErrorDto.builder().error(exception.getMessage()).build();
    }

    @ExceptionHandler(DataNotMatchesException.class)
    public ResponseEntity<ErrorDto> dataNotMatchesHandler(DataNotMatchesException exception){
        return ResponseEntity.status(410).body(ErrorDto.builder().error(exception.getMessage()).build());
    }

    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto dataAccessExceptionHandler(DataAccessException ex){
        return ErrorDto.builder().error(ex.getMessage()).build();
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto unknownExceptionsHandler(Throwable ex){
        return ErrorDto.builder().error(ex.getMessage()).build();
    }
}
