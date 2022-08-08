package dev.mattson.app;

import com.google.gson.Gson;
import dev.mattson.daos.EmployeeDAOLocal;
import dev.mattson.daos.EmployeeDAOPostgres;
import dev.mattson.daos.ExpenseDAOLocal;
import dev.mattson.daos.ExpenseDAOPostgres;
import dev.mattson.entities.Expense;
import dev.mattson.entities.ExpenseStatus;
import dev.mattson.handlers.employeehandlers.*;
import dev.mattson.handlers.expensehandlers.*;
import dev.mattson.services.EmployeeService;
import dev.mattson.services.EmployeeServiceImpl;
import dev.mattson.services.ExpenseService;
import dev.mattson.services.ExpenseServiceImpl;
import io.javalin.Javalin;
import io.javalin.http.Handler;

import java.util.List;


public class App {

    public static EmployeeService employeeService = new EmployeeServiceImpl(new EmployeeDAOPostgres());
    public static ExpenseService expenseService = new ExpenseServiceImpl(new ExpenseDAOPostgres());

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
        //GetAllExpensesHandler getAllExpensesHandler = new GetAllExpensesHandler();


        app.post("/expenses",createExpenseHandler);
        app.get("/expenses/{id}",getExpenseByIdHandler);
        app.delete("/expenses/{id}",deleteExpenseByIdHandler);
        app.put("/expenses/{id}",updateExpenseByIdHandler);
        //app.get("/expenses",getAllExpensesHandler);


        Handler getAllExpenses = ctx -> {
            String status = ctx.queryParam("expenseStatus");
            Gson gson = new Gson();

            if (status == null) {
                List<Expense> expenses = App.expenseService.getAllExpenses();
                String json = gson.toJson(expenses);
                ctx.result(json);
            }else {
                try {
                    ExpenseStatus expStatus = ExpenseStatus.valueOf(status.toUpperCase());
                    List<Expense> expensesByStatus = App.expenseService.getExpensesByExpenseStatus(status);
                    String json = gson.toJson(expensesByStatus);
                    ctx.result(json);
                }catch (IllegalArgumentException incorrectStatus) {
                    ctx.result("Improper status. Use any of the following: PENDING, APPROVED, DENIED");
                }

//                List<Expense> expensesByStatus = App.expenseService.getExpensesByExpenseStatus(expenseStatus);
//                String json = gson.toJson(expensesByStatus);
//                ctx.result(json);
            }
        };

        app.get("/expenses",getAllExpenses);




        app.start();
    }
}
