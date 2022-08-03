package dev.mattson.entities;

public class Expense {

    //variables
    private int id;
    private int employeeId;
    private double expense;
    private ExpenseType expenseType;
    private String expenseDescription;
    private ExpenseStatus expenseStatus;

    //Constructor
    public Expense(int id, int employeeId, double expense, ExpenseType expenseType, String expenseDescription, ExpenseStatus expenseStatus) {
        this.id = id;
        this.employeeId = employeeId;
        this.expense = expense;
        this.expenseType = expenseType;
        this.expenseDescription = expenseDescription;
        this.expenseStatus = expenseStatus;
    }

    //Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public double getExpense() {
        return expense;
    }

    public void setExpense(double expense) {
        this.expense = expense;
    }

    public ExpenseType getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(ExpenseType expenseType) {
        this.expenseType = expenseType;
    }

    public String getExpenseDescription() {
        return expenseDescription;
    }

    public void setExpenseDescription(String expenseDescription) {
        this.expenseDescription = expenseDescription;
    }

    public ExpenseStatus getExpenseStatus() {
        return expenseStatus;
    }

    public void setExpenseStatus(ExpenseStatus expenseStatus) {
        this.expenseStatus = expenseStatus;
    }

    //toString
    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", employeeId=" + employeeId +
                ", expense=" + expense +
                ", expenseType=" + expenseType +
                ", expenseDescription='" + expenseDescription + '\'' +
                ", expenseStatus=" + expenseStatus +
                '}';
    }
}
