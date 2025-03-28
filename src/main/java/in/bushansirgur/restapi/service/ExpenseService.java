package in.bushansirgur.restapi.service;

import in.bushansirgur.restapi.dto.ExpenseDTO;

import java.util.List;

public interface ExpenseService {
    List<ExpenseDTO> getAllExpenses();

    ExpenseDTO getExpenseByExpenseId(String expenseId);

    void deleteExpenseByExpenseId(String expenseId);
}
