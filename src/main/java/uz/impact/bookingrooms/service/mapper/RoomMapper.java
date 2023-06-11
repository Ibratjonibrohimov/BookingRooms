package uz.impact.bookingrooms.service.mapper;

import lombok.Data;
import uz.impact.bookingrooms.dto.RoomDto;
import uz.impact.bookingrooms.entity.Room;

@Data
public class RoomMapper {
    public static RoomDto toDto(Room room){
        return new RoomDto(
                room.getId(),
                room.getName(),
                room.getRoomType().getName(),
                room.getCapacity());
    }
}
