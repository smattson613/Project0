package dev.mattson.daos;

import dev.mattson.entities.Employee;

import java.util.List;

public interface EmployeeDAO {

    //Create an employee
    Employee createEmployee(Employee employee);

    //Read
    Employee getEmployeeById(int id);
    List<Employee> getAllEmployees();

    //Update an employee
    Employee updateEmployee(Employee employee);

    //Delete and employee
    boolean deleteEmployeeById(int id);
}
