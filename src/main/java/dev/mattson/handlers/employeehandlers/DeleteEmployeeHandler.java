package dev.mattson.handlers.employeehandlers;

import dev.mattson.app.App;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class DeleteEmployeeHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int id = Integer.parseInt(ctx.pathParam("id"));
        boolean result = App.employeeService.deleteEmployee(id);
        if (result) {
            ctx.result("Deleted Employee");
        } else {
            ctx.status(404);
            ctx.result("Delete request failed. Employee ID not found.");
        }
    }
}
