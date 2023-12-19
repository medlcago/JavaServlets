package task;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataGenerator {
    private static final Faker faker = new Faker();

    public static List<WatchsPerTwoMonths> generateData(int numOfMonths, int numOfDaysPerMonth, int maxNumOfViews) {
        List<WatchsPerTwoMonths> movieViewsList = new ArrayList<>();

        for (int i = 0; i < numOfMonths; i++) {
            for (int j = 0; j < numOfDaysPerMonth; j++) {
                LocalDate date = LocalDate.now().minusMonths(i).minusDays(j);
                int numOfViews = faker.number().numberBetween(0, maxNumOfViews);
                String movieName = faker.book().title();

                WatchsPerTwoMonths movieViews = new WatchsPerTwoMonths(date, numOfViews, movieName);
                movieViewsList.add(movieViews);
            }
        }
        return movieViewsList;
    }
}