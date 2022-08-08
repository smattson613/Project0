package dev.mattson.services;

import dev.mattson.entities.Expense;

import java.util.List;

public interface ExpenseService {

    Expense registerExpense(Expense expense);

    Expense retrieveExpenseByID(int id);

    boolean deleteExpense(int id);

    Expense modifyExpense(Expense expense);

    List<Expense> getAllExpenses();

    List<Expense> getExpensesByExpenseStatus(String status);
}
