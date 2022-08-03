package dev.mattson.services.expenseservice;

import dev.mattson.daos.ExpenseDAO;
import dev.mattson.entities.Expense;

public class ExpenseServiceImpl implements ExpenseService{

    private ExpenseDAO expenseDAO;

    public ExpenseServiceImpl(ExpenseDAO expenseDAO) {
        this.expenseDAO = expenseDAO;
    }
    @Override
    public Expense registerExpense(Expense expense) {
        if (expense.getExpense() < 0) {
        throw new RuntimeException("Expense value cannot be negative");
    }
        Expense savedExpense = this.expenseDAO.createExpense(expense);
        return savedExpense;
    }

    @Override
    public Expense retrieveExpenseByID(int id) {
        return this.expenseDAO.getExpenseById(id);
    }

    @Override
    public boolean deleteExpense(int id) {
        boolean isSuccessful = this.expenseDAO.deleteExpenseById(id);
        return isSuccessful;
    }

    @Override
    public Expense modifyExpense(Expense expense) {
        return this.expenseDAO.updateExpense(expense);
    }
}
