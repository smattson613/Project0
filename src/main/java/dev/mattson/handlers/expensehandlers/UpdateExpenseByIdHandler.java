package dev.mattson.handlers.expensehandlers;

import com.google.gson.Gson;
import dev.mattson.app.App;
import dev.mattson.entities.Expense;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class UpdateExpenseByIdHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Expense expense = App.expenseService.retrieveExpenseByID(id);
        if (expense != null) {
            String expenseJSON = ctx.body();
            Gson gson = new Gson();
            Expense expense2 = gson.fromJson(expenseJSON, Expense.class);
            expense2.setId(id);
            Expense updateExpense = App.expenseService.modifyExpense(expense2);
            String json = gson.toJson(updateExpense);
            ctx.result(json);
        } else {
            ctx.status(404);
            ctx.result("Request failed. Expense ID not found.");
        }
    }
}
