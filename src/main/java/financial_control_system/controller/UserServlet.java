package financial_control_system.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import financial_control_system.core.dto.UserDto;
import financial_control_system.service.api.IUserService;
import financial_control_system.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

@WebServlet(urlPatterns = "/user", name = "UserServlet")
public class UserServlet extends HttpServlet {
    private final IUserService userService = new UserService();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public UserServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        String userIdParam = req.getParameter("userId");

        if(userIdParam == null) {
            Set<UserDto> users = userService.getAll();
            for (UserDto userDto : users) {
                writer.println("<li>User ID: " + userDto.getUserId()
                        + ", Username: " + userDto.getUserName()
                        + ", Email: " + userDto.getEmail() + "</li>");
            }
        } else {
            long userId = Long.parseLong(userIdParam);
            UserDto userDto = userService.getById(userId);
            if(userDto != null) {
                writer.println("<li>User ID: " + userDto.getUserId()
                        + ", Username: " + userDto.getUserName()
                        + ", Email: " + userDto.getEmail() + "</li>");
            } else {
                writer.println("<li>User with ID " + userId + " not found.</li>");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html, charset=UTF-8");

        try (BufferedReader reader = req.getReader()) {
            StringBuilder requestBody = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                requestBody.append(line);
            }

            UserDto userDto = objectMapper.readValue(requestBody.toString(), UserDto.class);
            userService.create(userDto);
            resp.setStatus(HttpServletResponse.SC_CREATED);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        String userIdParam = req.getParameter("userId");
        long userId = Long.parseLong(userIdParam);

        StringBuilder requestBody = new StringBuilder();
        try (BufferedReader reader = req.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                requestBody.append(line);
            }
        }

        UserDto userDto = objectMapper.readValue(requestBody.toString(), UserDto.class);

        userService.update(userId, userDto.getEmail());
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html, charset=UTF-8");

        String userIdParam = req.getParameter("userId");

        userService.delete(Long.parseLong(userIdParam));
    }
}
