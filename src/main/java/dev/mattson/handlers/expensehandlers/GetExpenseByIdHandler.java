package dev.mattson.handlers.expensehandlers;

import com.google.gson.Gson;
import dev.mattson.app.App;
import dev.mattson.entities.Expense;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class GetExpenseByIdHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Expense expense = App.expenseService.retrieveExpenseByID(id);
        Gson gson = new Gson();
        String json = gson.toJson(expense);
        if (expense != null) {
            ctx.result(json);
        } else {
            ctx.status(404);
            ctx.result("Request failed. Expense ID not found.");
        }


    }
}
