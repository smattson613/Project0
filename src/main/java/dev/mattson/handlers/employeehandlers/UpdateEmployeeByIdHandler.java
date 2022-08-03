package dev.mattson.handlers.employeehandlers;

import com.google.gson.Gson;
import dev.mattson.app.App;
import dev.mattson.entities.Employee;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class UpdateEmployeeByIdHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Employee employee = App.employeeService.retrieveEmployeeById(id);
        if (employee != null) {
            String employeeJSON = ctx.body();
            Gson gson = new Gson();
            Employee employee2 = gson.fromJson(employeeJSON, Employee.class);
            employee2.setId(id);
            Employee updateEmployee = App.employeeService.modifyEmployee(employee2);
            String json = gson.toJson(updateEmployee);
            ctx.result(json);
        } else {
            ctx.status(404);
            ctx.result("Request failed. Employee ID not found.");
        }
    }
}
