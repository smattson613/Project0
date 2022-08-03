package dev.mattson.services.employeeservice;

import dev.mattson.entities.Employee;

public interface EmployeeService {

    Employee registerEmployee(Employee employee);

    Employee retrieveEmployeeById(int id);

    boolean deleteEmployee(int id);

    Employee modifyEmployee(Employee employee);
}
