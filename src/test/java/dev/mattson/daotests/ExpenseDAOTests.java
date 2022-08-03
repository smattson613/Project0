package dev.mattson.daotests;

import dev.mattson.daos.ExpenseDAO;
import dev.mattson.daos.ExpenseDAOLocal;
import dev.mattson.entities.Expense;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static dev.mattson.entities.ExpenseStatus.PENDING;
import static dev.mattson.entities.ExpenseType.TRAVEL;

public class ExpenseDAOTests {

    static ExpenseDAO expenseDAO = new ExpenseDAOLocal();

    @Test
    void create_expense_test() {
        Expense expense = new Expense(0,1,100.00,TRAVEL,"Description",PENDING);
        Expense savedExpense = expenseDAO.createExpense(expense);
        Assertions.assertNotEquals(0,savedExpense.getId());
    }

    @Test
    void get_expense_by_id_test() {
        Expense expense = new Expense(0,1,100.00,TRAVEL,"Description",PENDING);
        Expense savedExpense = expenseDAO.createExpense(expense);
        Expense expense2 = expenseDAO.getExpenseById(1);
        Assertions.assertEquals(100.00,expense2.getExpense());
    }

    @Test
    void update_expense_by_id_test() {
        Expense expense = new Expense(0,1,100.00,TRAVEL,"Description",PENDING);
        expenseDAO.createExpense(expense);
        Expense expense2 = new Expense(1,2,50.00,TRAVEL,"Description",PENDING);
        expenseDAO.updateExpense(expense2);
        Assertions.assertEquals(50.00,expense2.getExpense());
    }

    @Test
    void delete_expense_by_id_test() {
        Expense expense = new Expense(0,1,100.00,TRAVEL,"Description",PENDING);
        Expense savedExpense = expenseDAO.createExpense(expense);
        boolean result = expenseDAO.deleteExpenseById(1);
        Assertions.assertTrue(result);
    }
}
