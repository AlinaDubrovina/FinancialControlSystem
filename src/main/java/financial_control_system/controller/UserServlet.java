package financial_control_system.controller;

import financial_control_system.core.dto.UserDto;
import financial_control_system.service.IUserService;
import financial_control_system.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

@WebServlet(urlPatterns = "/user", name = "UserServlet")
public class UserServlet extends HttpServlet {
    private final IUserService userService = new UserService();

    public UserServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html, charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        Set<UserDto> users = userService.getAll();
        for (UserDto userDto : users) {
            writer.println("<li>User ID: " + userDto.getUserId()
                    + ", Username: " + userDto.getUserName()
                    + ", Email: " + userDto.getEmail() + "</li>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html, charset=UTF-8");

        String userName = req.getParameter("userName");
        String email = req.getParameter("email");

        UserDto userDto = new UserDto();
        userDto.setUserName(userName);
        userDto.setEmail(email);

        userService.create(userDto);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
