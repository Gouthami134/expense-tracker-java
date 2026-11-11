
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** A simple in-memory expense tracker for practicing Java records and collections. */
public class ExpenseTracker {
    private static final Scanner INPUT = new Scanner(System.in);
    private static final List<Expense> EXPENSES = new ArrayList<>();
    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\n=== Expense Tracker ===");
            System.out.println("1. Add expense\n2. List expenses\n3. Show total\n0. Exit");
            switch (readInt("Choose: ")) {
                case 1 -> addExpense();
                case 2 -> listExpenses();
                case 3 -> showTotal();
                case 0 -> running = false;
                default -> System.out.println("Choose one of the listed options.");
            }
        }
        System.out.println("Goodbye.");
    }
    private static void addExpense() {
        System.out.print("Category: ");
        String category = INPUT.nextLine().trim();
        if (category.isEmpty()) { System.out.println("Category cannot be empty."); return; }
        System.out.print("Description: ");
        String description = INPUT.nextLine().trim();
        double amount = readAmount("Amount: ");
        if (description.isEmpty() || amount <= 0) { System.out.println("Description is required and amount must be positive."); return; }
        EXPENSES.add(new Expense(LocalDate.now(), category, description, amount));
        System.out.println("Expense recorded.");
    }
    private static void listExpenses() {
        if (EXPENSES.isEmpty()) { System.out.println("No expenses recorded."); return; }
        EXPENSES.forEach(System.out::println);
    }
    private static void showTotal() {
        double total = EXPENSES.stream().mapToDouble(Expense::amount).sum();
        System.out.printf("Total expenses: %.2f%n", total);
    }
    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try { return Integer.parseInt(INPUT.nextLine().trim()); }
            catch (NumberFormatException ex) { System.out.println("Please enter a whole number."); }
        }
    }
    private static double readAmount(String prompt) {
        while (true) {
            System.out.print(prompt);
            try { return Double.parseDouble(INPUT.nextLine().trim()); }
            catch (NumberFormatException ex) { System.out.println("Please enter a number, such as 125.50."); }
        }
    }
    private record Expense(LocalDate date, String category, String description, double amount) {
        @Override public String toString() { return String.format("%s | %-14s | %-24s | %.2f", date, category, description, amount); }
    }
}
