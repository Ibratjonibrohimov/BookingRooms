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
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer page;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer count;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "page_size")
    private Integer pageSize;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<RoomDto> results;
}
