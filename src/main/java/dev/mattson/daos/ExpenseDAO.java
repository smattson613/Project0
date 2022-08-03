package dev.mattson.daos;

import dev.mattson.entities.Expense;

import java.util.List;

public interface ExpenseDAO {

    //Create and expense
    Expense createExpense(Expense expense);

    //Read
    Expense getExpenseById(int expense);
    List<Expense> getAllExpenses();

    //Update an expense
    Expense updateExpense(Expense expense);

    //Delete an Expense
    boolean deleteExpenseById(int id);

}
