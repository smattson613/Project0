package dev.mattson.handlers.employeehandlers;

import com.google.gson.Gson;
import dev.mattson.app.App;
import dev.mattson.entities.Employee;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class GetEmployeeByIdHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Employee employee = App.employeeService.retrieveEmployeeById(id);
        Gson gson = new Gson();
        String json = gson.toJson(employee);
        if (employee != null) {
            ctx.result(json);
        } else {
            ctx.status(404);
            ctx.result("Request failed. Employee ID not found.");
        }
    }
}
