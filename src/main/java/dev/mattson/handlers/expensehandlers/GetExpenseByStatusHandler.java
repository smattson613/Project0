package dev.mattson.handlers.expensehandlers;

import dev.mattson.app.App;
import dev.mattson.entities.ExpenseStatus;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class GetExpenseByStatusHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
//        String status = ctx.queryParam("expenseStatus");




    }
}
