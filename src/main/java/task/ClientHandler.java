package task;

import java.util.Comparator;
import java.util.List;

public class ClientHandler {
    public Result process(List<WatchsPerTwoMonths> data) {
        WatchsPerTwoMonths maxViewsDay = data.stream()
                .max(Comparator.comparing(WatchsPerTwoMonths::getNumOfViews))
                .orElse(null);

        double averageViews = data.stream()
                .mapToInt(WatchsPerTwoMonths::getNumOfViews)
                .average()
                .orElse(0.0);


        assert maxViewsDay != null;
        return new Result(
                maxViewsDay.getDate(),
                maxViewsDay.getNumOfViews(),
                averageViews,
                maxViewsDay.getMovieName()
        );
    }
}
