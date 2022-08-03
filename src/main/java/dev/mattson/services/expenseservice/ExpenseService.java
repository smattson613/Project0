package dev.mattson.services.expenseservice;

import dev.mattson.entities.Expense;

public interface ExpenseService {

    Expense registerExpense(Expense expense);

    Expense retrieveExpenseByID(int id);

    boolean deleteExpense(int id);

    Expense modifyExpense(Expense expense);
}
