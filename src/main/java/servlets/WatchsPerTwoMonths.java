package servlets;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class WatchsPerTwoMonths {
    @JsonProperty("date")
    private LocalDate date;

    @JsonProperty("numOfViews")
    private int numOfViews;

    @JsonProperty("movieName")
    private String movieName;
}

