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
    @JsonProperty(index = 0)
    private Integer page;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(index = 1)
    private Integer count;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "page_size",index = 2)
    private Integer pageSize;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(index = 3)
    private List<RoomDto> results;
}
