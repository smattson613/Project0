package dev.mattson.handlers.employeehandlers;

import com.google.gson.Gson;
import dev.mattson.app.App;
import dev.mattson.daos.EmployeeDAOLocal;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class GetAllEmployeesHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        Gson gson = new Gson();
        String json = gson.toJson(EmployeeDAOLocal.employeeTable);
        ctx.result(json);
    }
}
