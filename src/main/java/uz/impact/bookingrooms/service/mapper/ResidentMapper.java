package uz.impact.bookingrooms.service.mapper;

import uz.impact.bookingrooms.dto.ResidentDto;
import uz.impact.bookingrooms.entity.Resident;

public class ResidentMapper {
    public static ResidentDto toDto(Resident resident){
        return new ResidentDto(resident.getName());
    }

    public static Resident toEntity(ResidentDto residentDto) {
        return new Resident(residentDto.getName());
    }
}
