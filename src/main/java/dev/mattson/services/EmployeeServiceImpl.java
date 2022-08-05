package dev.mattson.services;

import dev.mattson.daos.EmployeeDAO;
import dev.mattson.entities.Employee;

public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeDAO employeeDAO;

    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public Employee registerEmployee(Employee employee) {
        Employee savedEmployee = this.employeeDAO.createEmployee(employee);
        return savedEmployee;
    }

    @Override
    public Employee retrieveEmployeeById(int id) {
        return this.employeeDAO.getEmployeeById(id);
    }

    @Override
    public boolean deleteEmployee(int id) {
        boolean isSuccessful = this.employeeDAO.deleteEmployeeById(id);
        return isSuccessful;
    }

    @Override
    public Employee modifyEmployee(Employee employee) {
        return this.employeeDAO.updateEmployee(employee);
    }
}
