package dev.mattson.daotests;

import dev.mattson.daos.EmployeeDAO;
import dev.mattson.daos.EmployeeDAOLocal;
import dev.mattson.entities.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EmployeeDAOTests {

    static EmployeeDAO employeeDAO = new EmployeeDAOLocal();

    @Test
    void create_employee_test(){
        Employee employee = new Employee(0,"Stephen","Mattson");
        Employee savedEmployee = employeeDAO.createEmployee(employee);
        Assertions.assertNotEquals(0,savedEmployee.getId());
    }

    @Test
    void get_employee_by_id_test(){
        Employee employee = new Employee(0,"Stephen","Mattson");
        Employee savedEmployee = employeeDAO.createEmployee(employee);
        Employee employee2 = employeeDAO.getEmployeeById(1);
        Assertions.assertEquals("Stephen",employee2.getFirstName());
    }

    @Test
    void update_employee_by_id_test(){
        Employee employee = new Employee(0,"Stephen","Mattson");
        employeeDAO.createEmployee(employee);
        Employee employee2 = new Employee(1,"Will","Tring");
        employeeDAO.updateEmployee(employee2);
        Employee updatedEmployee = employeeDAO.getEmployeeById(1);
        Assertions.assertEquals("Will", updatedEmployee.getFirstName());
    }

    @Test
    void delete_employee_by_id_test(){
        Employee employee = new Employee(0,"Stephen","Mattson");
        Employee savedEmployee = employeeDAO.createEmployee(employee);
        boolean result = employeeDAO.deleteEmployeeById(1);
        Assertions.assertTrue(result);
    }
}
