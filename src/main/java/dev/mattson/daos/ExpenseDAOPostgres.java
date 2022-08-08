package dev.mattson.daos;

import dev.mattson.entities.Expense;
import dev.mattson.entities.ExpenseType;
import dev.mattson.entities.ExpenseStatus;
import dev.mattson.services.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDAOPostgres implements ExpenseDAO {
    @Override
    public Expense createExpense(Expense expense) {
        try (Connection conn = ConnectionUtil.createConnection()) {
            String sql = "insert into expense values (default, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, expense.getEmployeeId());
            preparedStatement.setDouble(2, expense.getExpense());
            preparedStatement.setString(3, expense.getExpenseType().name());
            preparedStatement.setString(4, expense.getExpenseDescription());
            preparedStatement.setString(5, expense.getExpenseStatus().name());

            preparedStatement.execute();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();

            int generatedKey = rs.getInt("id");
            expense.setId(generatedKey);
            return expense;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Expense getExpenseById(int id) {
        try(Connection conn = ConnectionUtil.createConnection()) {
            String sql = "select * from expense where id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            rs.next();

            Expense expense = new Expense();
            expense.setId(rs.getInt("id"));
            expense.setEmployeeId(rs.getInt("employeeId"));
            expense.setExpense(rs.getDouble("expenseAmount"));
            expense.setExpenseType(ExpenseType.valueOf(rs.getString("expenseType")));
            expense.setExpenseDescription(rs.getString("expenseDescription"));
            expense.setExpenseStatus(ExpenseStatus.valueOf(rs.getString("expenseStatus")));

            return expense;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Expense> getAllExpenses() {
        try(Connection conn = ConnectionUtil.createConnection()) {
            String sql = "select * from expense";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();

            List<Expense> expenseList = new ArrayList<>();
            while (rs.next()) {
                Expense expense = new Expense();
                expense.setId(rs.getInt("id"));
                expense.setEmployeeId(rs.getInt("employeeId"));
                expense.setExpense(rs.getDouble("expenseAmount"));
                expense.setExpenseType(ExpenseType.valueOf(rs.getString("expenseType")));
                expense.setExpenseDescription(rs.getString("expenseDescription"));
                expense.setExpenseStatus(ExpenseStatus.valueOf(rs.getString("expenseStatus")));
                expenseList.add(expense);
            }
            return expenseList;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Expense updateExpense(Expense expense) {
        try(Connection conn = ConnectionUtil.createConnection()) {
            String sql = "update expense set employeeId = ?, expenseAmount = ?, expenseType = ?, expenseDescription = ?, expenseStatus = ? where id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setInt(1, expense.getEmployeeId());
            preparedStatement.setDouble(2, expense.getExpense());
            preparedStatement.setString(3, expense.getExpenseType().name());
            preparedStatement.setString(4, expense.getExpenseDescription());
            preparedStatement.setString(5, expense.getExpenseStatus().name());
            preparedStatement.setInt(6, expense.getId());

            preparedStatement.executeUpdate();
            return expense;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteExpenseById(int id) {
        try(Connection conn = ConnectionUtil.createConnection()) {
            String sql = "delete from expense where id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setInt(1, id);

            preparedStatement.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
