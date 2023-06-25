package uz.impact.bookingrooms.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto {
    @JsonProperty(index = 0)
    private Long id;
    @JsonProperty(index = 1)
    private String name;
    @JsonProperty(index = 2)
    private String type;
    @JsonProperty(index = 3)
    private Integer capacity;
}
