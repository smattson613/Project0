package dev.mattson.handlers.expensehandlers;

import com.google.gson.Gson;
import dev.mattson.daos.ExpenseDAOLocal;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class GetAllExpensesHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        Gson gson = new Gson();
        String json = gson.toJson(ExpenseDAOLocal.expenseTable);
        ctx.result(json);
    }
}
