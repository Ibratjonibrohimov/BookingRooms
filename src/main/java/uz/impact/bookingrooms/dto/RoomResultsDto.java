package uz.impact.bookingrooms.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomResultsDto {

    @JsonProperty(index = 0,defaultValue = "0")
    private Integer page;

    @JsonProperty(index = 1,defaultValue = "0")
    private Integer count;

    @JsonProperty(value = "page_size",index = 2,defaultValue = "0")
    private Integer pageSize;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(index = 3)
    private List<RoomDto> results;
}
