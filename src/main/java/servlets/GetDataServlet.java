package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import task.DataGenerator;
import task.WatchsPerTwoMonths;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/get-data")
public class GetDataServlet extends HttpServlet {
    private static final ObjectMapper objectMapper = new JsonMapper();
    private static final int NUM_OF_MONTHS = 2;
    private static final int NUM_OF_DAYS_PER_MONTH = 30;
    private static final int MAX_NUM_OF_VIEWS = 1000;

    static {
        objectMapper.registerModule(new JavaTimeModule());
        SimpleModule module = new SimpleModule();
        module.addSerializer(LocalDate.class, ToStringSerializer.instance);
        objectMapper.registerModule(module);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<WatchsPerTwoMonths> movieViewsList = DataGenerator.generateData(NUM_OF_MONTHS, NUM_OF_DAYS_PER_MONTH, MAX_NUM_OF_VIEWS);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(objectMapper.writeValueAsString(movieViewsList));
        } catch (Exception e) {
            resp.setContentType("application/json");
            resp.setStatus(500);
            resp.getWriter().write("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }
}
