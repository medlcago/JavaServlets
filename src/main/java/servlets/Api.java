package servlets;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import java.util.List;

public interface Api {
    @GET("/webapp/get-data")
    Call<List<WatchsPerTwoMonths>> getData();

    @POST("/webapp/statistics")
    Call<Result> postData(@Body List<WatchsPerTwoMonths> request);
}
