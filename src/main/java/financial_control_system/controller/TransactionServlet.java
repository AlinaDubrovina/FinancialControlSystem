package financial_control_system.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import financial_control_system.core.dto.TransactionDto;
import financial_control_system.service.TransactionService;
import financial_control_system.service.api.ITransactionService;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

@WebServlet(urlPatterns = "/transaction", name = "TransactionServlet")
public class TransactionServlet extends HttpServlet {
    private final ITransactionService transactionService = new TransactionService();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public TransactionServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        String accountIdParam = req.getParameter("account_id");

        List<TransactionDto> transactions = transactionService.getByAccountId(Long.parseLong(accountIdParam));
        for (TransactionDto transactionDto : transactions) {
            writer.println("<li>Transaction ID: " + transactionDto.getTransactionId()
                    + ", Amount: " + transactionDto.getAmount()
                    + ", Description: " + transactionDto.getDescription()
                    + ", Account ID: " + transactionDto.getAccountId() + "</li>");
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

            TransactionDto transactionDto = objectMapper.readValue(requestBody.toString(), TransactionDto.class);
            transactionService.create(transactionDto);
            resp.setStatus(HttpServletResponse.SC_CREATED);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        String transactionIdParam = req.getParameter("transaction_id");
        UUID transactionId = UUID.fromString(transactionIdParam);

        StringBuilder requestBody = new StringBuilder();
        try (BufferedReader reader = req.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                requestBody.append(line);
            }
        }

        TransactionDto transactionDto = objectMapper.readValue(requestBody.toString(), TransactionDto.class);

        transactionService.update(transactionId, transactionDto.getDescription());
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html, charset=UTF-8");

        String transactionIdParam = req.getParameter("transaction_id");

        transactionService.delete(UUID.fromString(transactionIdParam));
    }
}
