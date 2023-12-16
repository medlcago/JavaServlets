package servlets;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/statistics")
public class StatisticsServlet extends HttpServlet {
    private static final ObjectMapper objectMapper = new JsonMapper();

    static {
        objectMapper.registerModule(new JavaTimeModule());
        SimpleModule module = new SimpleModule();
        module.addSerializer(LocalDate.class, ToStringSerializer.instance);
        objectMapper.registerModule(module);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Получение данных от клиента
        try {

            List<WatchsPerTwoMonths> data = objectMapper.readValue(req.getReader(), new TypeReference<>() {
            });

            // Создание объекта servlets.ClientHandler и вызов метода process()
            ClientHandler clientHandler = new ClientHandler();
            Result result = clientHandler.process(data);

            // Приостановка выполнения на 3 секунды
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Преобразование результата в JSON и отправка клиенту
            resp.setContentType("application/json");
            resp.setStatus(200);
            resp.getWriter().write(objectMapper.writeValueAsString(result));
        } catch (Exception e) {
            resp.setContentType("application/json");
            resp.setStatus(500);
            resp.getWriter().write("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }
}
