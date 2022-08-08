package dev.mattson.daotests;

import dev.mattson.daos.ExpenseDAO;
import dev.mattson.daos.ExpenseDAOLocal;
import dev.mattson.daos.ExpenseDAOPostgres;
import dev.mattson.entities.Expense;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static dev.mattson.entities.ExpenseStatus.*;
import static dev.mattson.entities.ExpenseType.*;

public class ExpenseDAOTests {

    static ExpenseDAO expenseDAO = new ExpenseDAOPostgres();

    @Test
    void create_expense_test() {
        Expense expense = new Expense(0,1,100.00,TRAVEL,"Description",PENDING);
        Expense savedExpense = expenseDAO.createExpense(expense);
        Assertions.assertEquals(1,savedExpense.getEmployeeId());
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
        Expense updatedExpense = expenseDAO.getExpenseById(1);
        Assertions.assertEquals(50.00,updatedExpense.getExpense());
    }

    @Test
    void delete_expense_by_id_test() {
        Expense expense = new Expense(0,1,100.00,TRAVEL,"Description",PENDING);
        Expense savedExpense = expenseDAO.createExpense(expense);
        boolean result = expenseDAO.deleteExpenseById(1);
        Assertions.assertTrue(result);
    }

    @Test
    void get_all_expenses_test () {
        Expense expense1 = new Expense(0,101,50.00,TRAVEL,"gas",PENDING);
        Expense expense2 = new Expense(0,102,200,LODGING,"Marriott",APPROVED);
        Expense expense3 = new Expense(0,103,75,FOOD,"Chophouse 42",DENIED);

        expenseDAO.createExpense(expense1);
        expenseDAO.createExpense(expense2);
        expenseDAO.createExpense(expense3);

        List<Expense> expenseList = expenseDAO.getAllExpenses();
        Assertions.assertEquals(3,expenseList.size());
    }
}
