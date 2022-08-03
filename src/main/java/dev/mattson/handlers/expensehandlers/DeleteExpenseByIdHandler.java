package dev.mattson.handlers.expensehandlers;

import dev.mattson.app.App;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class DeleteExpenseByIdHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int id = Integer.parseInt(ctx.pathParam("id"));
        boolean result = App.expenseService.deleteExpense(id);
        if (result) {
            ctx.result("Deleted Expense");
        } else {
            ctx.status(404);
            ctx.result("Delete request failed. Expense ID not found");
        }
    }
}
