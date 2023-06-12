package uz.impact.bookingrooms.service;

import org.springframework.http.ResponseEntity;
import uz.impact.bookingrooms.dto.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface RoomService {

    /** Berilgan   parametrlar  bo'yicha xonalarni topib RoomResultDto  obyekti ko'rinishida  qaytaradi**/
    ResponseEntity<RoomResultsDto> getAllRooms(Optional<String> search, Optional<String> type, Optional<Integer> page, Optional<Integer> pageSize);

        /** Berilgan  id bo'yicha  xonani  qaytaradi. Agar id  bo'yicha  xona  topilmasa  ErrorDto  ni 404 Response ga  o'rab  qaytaradi**/
    ResponseEntity<RoomDto> getById(Long id);


    /**Berilgan id  bo'yicha xonaning berilgan date band bo'lmagan  vaqtlarini List<FreeTimeDto> ko'rinishida qaytaradi( date parametr "yyyy-MM-dd" ko'rinishida yuboriladi) .Agar id  bo'yicha  xona  topilmasa  ErrorDto  ni 404 Response ga  o'rab  qaytaradi.Agar date parametr  berilmasa  bugungi   kunni  oladi. **/
    ResponseEntity<List<FreeTimeDto>> getAvailableTime(Long id, Optional<LocalDate> date);

    /**Berilgan id  bo'yicha xonani yuborilgan vaqtlarga  band  qilib  qo'yadi. Agar xona  yuborilgan  vaqtda  band  bo'lsa  ErrorDto ni 410 Response ga o'rab qaytaradi **/
    ResponseEntity<ResponseDto> bookRoom(Long id, BookingDto book);
}
