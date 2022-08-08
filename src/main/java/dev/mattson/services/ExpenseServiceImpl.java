package dev.mattson.services;

import dev.mattson.daos.ExpenseDAO;
import dev.mattson.entities.Expense;
import dev.mattson.entities.ExpenseStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ExpenseServiceImpl implements ExpenseService {

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

    @Override
    public List<Expense> getAllExpenses() {
        return this.expenseDAO.getAllExpenses();
    }

    @Override
    public List<Expense> getExpensesByExpenseStatus(String status) {
        List<Expense> allExpenses = this.getAllExpenses();
//        List<Expense> sortedExpenseList = new ArrayList();
//
//        for (Expense expense : allExpenses) {
//            if(expense.getExpenseStatus().equals(status)) {
//                sortedExpenseList.add(expense);
//            }
//        }

        ExpenseStatus expenseStatus = ExpenseStatus.valueOf(status.toUpperCase());
        List<Expense> sortedExpenseList = allExpenses.stream().filter(expense -> expense.getExpenseStatus().equals(expenseStatus)).collect(Collectors.toList());
        return sortedExpenseList;
    }
}
