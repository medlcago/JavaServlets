package servlets;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class WatchsPerTwoMonths {
    @JsonProperty("date")
    private LocalDate date;

    @JsonProperty("numOfViews")
    private int numOfViews;

    @JsonProperty("movieName")
    private String movieName;

    public WatchsPerTwoMonths(LocalDate date, int numOfViews, String movieName) {
        this.date = date;
        this.numOfViews = numOfViews;
        this.movieName = movieName;
    }
}

