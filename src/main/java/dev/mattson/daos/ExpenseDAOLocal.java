package dev.mattson.daos;

import dev.mattson.entities.Expense;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseDAOLocal implements ExpenseDAO{

    public static Map<Integer,Expense> expenseTable = new HashMap();
    private int idMaker = 1;

    @Override
    public Expense createExpense(Expense expense) {
        expense.setId(idMaker);
        idMaker++;
        expenseTable.put(expense.getId(), expense);
        return expense;
    }

    @Override
    public Expense getExpenseById(int id) {
        return expenseTable.get(id);
    }

    @Override
    public List<Expense> getAllExpenses() {
        return null;
    }

    @Override
    public Expense updateExpense(Expense expense) {
        expenseTable.put(expense.getId(), expense);
        return expense;
    }

    @Override
    public boolean deleteExpenseById(int id) {
        Expense expense = expenseTable.remove(id);
        if (expense == null) {
            return false;
        }
        return true;
    }
}
