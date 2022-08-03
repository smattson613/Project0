package dev.mattson.app;

import dev.mattson.daos.EmployeeDAOLocal;
import dev.mattson.daos.ExpenseDAOLocal;
import dev.mattson.handlers.employeehandlers.*;
import dev.mattson.handlers.expensehandlers.*;
import dev.mattson.services.employeeservice.EmployeeService;
import dev.mattson.services.employeeservice.EmployeeServiceImpl;
import dev.mattson.services.expenseservice.ExpenseService;
import dev.mattson.services.expenseservice.ExpenseServiceImpl;
import io.javalin.Javalin;

public class App {

    public static EmployeeService employeeService = new EmployeeServiceImpl(new EmployeeDAOLocal());
    public static ExpenseService expenseService = new ExpenseServiceImpl(new ExpenseDAOLocal());

    public static void main(String[] args) {
        //Employee Handlers
        Javalin app = Javalin.create();
        CreateEmployeeHandler createEmployeeHandler = new CreateEmployeeHandler();
        DeleteEmployeeHandler deleteEmployeeHandler = new DeleteEmployeeHandler();
        GetEmployeeByIdHandler getEmployeeByIdHandler = new GetEmployeeByIdHandler();
        UpdateEmployeeByIdHandler updateEmployeeByIdHandler = new UpdateEmployeeByIdHandler();
        GetAllEmployeesHandler getAllEmployeesHandler = new GetAllEmployeesHandler();


        app.post("/employees",createEmployeeHandler);
        app.delete("/employees/{id}",deleteEmployeeHandler);
        app.get("/employees/{id}",getEmployeeByIdHandler);
        app.put("/employees/{id}",updateEmployeeByIdHandler);
        app.get("/employees",getAllEmployeesHandler);

        //Expense Handlers
        CreateExpenseHandler createExpenseHandler = new CreateExpenseHandler();
        GetExpenseByIdHandler getExpenseByIdHandler = new GetExpenseByIdHandler();
        DeleteExpenseByIdHandler deleteExpenseByIdHandler = new DeleteExpenseByIdHandler();
        UpdateExpenseByIdHandler updateExpenseByIdHandler = new UpdateExpenseByIdHandler();
        GetAllExpensesHandler getAllExpensesHandler = new GetAllExpensesHandler();


        app.post("/expenses",createExpenseHandler);
        app.get("/expenses/{id}",getExpenseByIdHandler);
        app.delete("/expenses/{id}",deleteExpenseByIdHandler);
        app.put("/expenses/{id}",updateExpenseByIdHandler);
        app.get("/expenses",getAllExpensesHandler);


        app.start();
    }
}
