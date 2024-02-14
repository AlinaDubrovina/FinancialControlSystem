package financial_control_system.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import financial_control_system.core.dto.AccountDto;
import financial_control_system.service.AccountService;
import financial_control_system.service.api.IAccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

@WebServlet(urlPatterns = "/account", name = "AccountServlet")
public class AccountServlet extends HttpServlet {
    private final IAccountService accountService = new AccountService();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public AccountServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        String accountIdParam = req.getParameter("account_id");

        if(accountIdParam == null) {
            Set<AccountDto> accounts = accountService.getAll();
            for (AccountDto account : accounts) {
                writer.println("<li>Account ID: " + account.getAccountId()
                        + ", Balance: " + account.getBalance()
                        + ", User: " + account.getUserId() + "</li>");
            }
        } else {
            long accountId = Long.parseLong(accountIdParam);
            AccountDto accountDto = accountService.getById(accountId);
            if(accountDto != null) {
                writer.println("<li>Account ID: " + accountDto.getAccountId()
                        + ", Balance: " + accountDto.getBalance()
                        + ", User ID: " + accountDto.getUserId() + "</li>");
            } else {
                writer.println("<li>Account with ID " + accountId + " not found.</li>");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        try (BufferedReader reader = req.getReader()) {
            StringBuilder requestBody = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                requestBody.append(line);
            }

            AccountDto accountDto = objectMapper.readValue(requestBody.toString(), AccountDto.class);
            accountService.create(accountDto);
            resp.setStatus(HttpServletResponse.SC_CREATED);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html, charset=UTF-8");

        String accountId = req.getParameter("account_id");

        StringBuilder requestBody = new StringBuilder();
        try (BufferedReader reader = req.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                requestBody.append(line);
            }
        }
        AccountDto accountDto = objectMapper.readValue(requestBody.toString(), AccountDto.class);
        accountService.update(Long.parseLong(accountId), accountDto.getBalance());
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html, charset=UTF-8");

        String accountIdParam = req.getParameter("account_id");

        accountService.delete(Long.parseLong(accountIdParam));
    }
}
