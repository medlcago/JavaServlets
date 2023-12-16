package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.List;

public class Main {
    private static final ObjectMapper objectMapper = new JsonMapper();

    static {
        objectMapper.registerModule(new JavaTimeModule());
    }

    private static final String BASE_URL = "http://127.0.0.1:8080";

    public static void main(String[] args) throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .build();

        Api api = retrofit.create(Api.class);
        // Отправляем GET-запрос и получаем сгенерированные данные
        Call<List<WatchsPerTwoMonths>> getDataCall = api.getData();
        List<WatchsPerTwoMonths> getData = getDataCall.execute().body();
        System.out.println("Сгенерированные данные:\n" + getData);

        // Отправляем POST-запрос и получаем обработанные данные
        Call<Result> postDataCall = api.postData(getData);
        Result postData = postDataCall.execute().body();
        System.out.println("\nОбработанные данные:\n" + postData);
        assert postData != null;
        System.out.println("\nmaxViewsDay: " + postData.getMaxViewsDay());
        System.out.println("maxViewsCount: " + postData.getMaxViewsCount());
        System.out.println("averageViewsPerDay: " + postData.getAverageViewsPerDay());
    }
}
