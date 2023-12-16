package servlets;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private LocalDate maxViewsDay;
    private int maxViewsCount;
    private double averageViewsPerDay;
}