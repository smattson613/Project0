package dev.mattson.daos;

import dev.mattson.entities.Employee;
import dev.mattson.services.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOPostgres implements EmployeeDAO{
    @Override
    public Employee createEmployee(Employee employee) {
        try(Connection conn = ConnectionUtil.createConnection()) {
            String sql = "insert into employee values (default, ?, ?);";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());

            preparedStatement.execute();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();

            int generatedKey = rs.getInt("id");
            employee.setId(generatedKey);
            return employee;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Employee getEmployeeById(int id) {
        try(Connection conn = ConnectionUtil.createConnection()) {
            String sql = "select * from employee where id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,id);

            ResultSet rs = preparedStatement.executeQuery();
            rs.next();

            Employee employee = new Employee();
            employee.setId(rs.getInt("id"));
            employee.setFirstName(rs.getString("firstName"));
            employee.setLastName(rs.getString("lastName"));

            return employee;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        try(Connection conn = ConnectionUtil.createConnection()) {
            String sql = "select * from employee";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();

            List<Employee> employeeList = new ArrayList<>();
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setFirstName(rs.getString("firstName"));
                employee.setLastName(rs.getString("lastName"));
                employeeList.add(employee);
            }
            return employeeList;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        try(Connection conn = ConnectionUtil.createConnection()) {
            String sql = "update employee set firstName = ?, lastName = ? where id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setInt(3, employee.getId());

            preparedStatement.executeUpdate();
            return employee;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteEmployeeById(int id) {
        try (Connection conn = ConnectionUtil.createConnection()) {
            String sql = "delete from employee where id = ?";

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
