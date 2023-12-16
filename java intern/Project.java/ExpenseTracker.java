import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

class Expense implements Serializable {
    private static final long serialVersionUID = 1L;

    String category;
    double amount;
    String description;
    Date date;
    Date dueDate; // New field for due date

    Expense(String category, double amount, String description, Date date, Date dueDate) {
        this.category = category;
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return "Date: " + dateFormat.format(date) + ", Category: " + category +
                ", Amount: $" + amount + ", Description: " + description +
                ", Due Date: " + (dueDate != null ? dateFormat.format(dueDate) : "Not set");
    }
}

public class ExpenseTracker {
    private static ArrayList<Expense> expenses = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static final String DATA_FILE = "expenses.dat";
    private static Map<String, Double> budgets = new HashMap<>();
    private static Timer timer = new Timer();

    public static void main(String[] args) {
        loadExpenses();
        loadBudgets();

        // Schedule reminders every 24 hours
        timer.scheduleAtFixedRate(new ReminderTask(), 0, 24 * 60 * 60 * 1000);

        while (true) {
            System.out.println("\nExpense Tracker Menu:");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Set Budget");
            System.out.println("4. Visualize Expenses");
            System.out.println("5. Save and Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addExpense();
                    break;
                case 2:
                    viewExpenses();
                    break;
                case 3:
                    setBudget();
                    break;
                case 4:
                    visualizeExpenses();
                    break;
                case 5:
                    saveExpenses();
                    saveBudgets();
                    timer.cancel(); // Stop the timer when exiting the program
                    System.out.println("Expenses and budgets saved. Exiting Expense Tracker. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void addExpense() {
        System.out.print("\nEnter expense category: ");
        String category = scanner.next();

        System.out.print("Enter expense amount: $");
        double amount = scanner.nextDouble();

        System.out.print("Enter expense description: ");
        String description = scanner.nextLine(); // Consume the newline character
        description = scanner.nextLine(); // Read the actual description

        System.out.print("Enter due date for the expense (yyyy-MM-dd), or leave it blank: ");
        String dueDateStr = scanner.nextLine().trim();
        Date dueDate = null;
        if (!dueDateStr.isEmpty()) {
            try {
                dueDate = new SimpleDateFormat("yyyy-MM-dd").parse(dueDateStr);
            } catch (java.text.ParseException e) {
                System.out.println("Invalid date format. Due date not set.");
            }
        }

        Date date = new Date(); // Use the current date and time

        Expense expense = new Expense(category, amount, description, date, dueDate);
        expenses.add(expense);

        // Check for overspending
        if (budgets.containsKey(category) && amount > budgets.get(category)) {
            System.out.println("Warning: You have exceeded the budget for category '" + category + "'!");
        }

        System.out.println("Expense added successfully!");
    }

    private static void viewExpenses() {
        System.out.println("\nExpense List:");
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded.");
        } else {
            for (Expense expense : expenses) {
                System.out.println(expense);
            }
        }
    }

    private static void setBudget() {
        System.out.print("\nEnter expense category for budget setting: ");
        String category = scanner.next();

        System.out.print("Enter budget amount for category '" + category + "': $");
        double budgetAmount = scanner.nextDouble();

        budgets.put(category, budgetAmount);
        System.out.println("Budget set successfully for category '" + category + "'.");
    }

    private static void visualizeExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded for visualization.");
            return;
        }

        DefaultPieDataset dataset = new DefaultPieDataset();
        for (Expense expense : expenses) {
            dataset.setValue(expense.category, dataset.getValue(expense.category) + expense.amount);
        }

        JFreeChart chart = ChartFactory.createPieChart(
                "Expense Distribution", // chart title
                dataset, // data
                true, // include legend
                true,
                false);

        JFrame frame = new JFrame("Expense Visualization");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(560, 370));
        frame.setContentPane(chartPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void saveExpenses() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(expenses);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error saving expenses.");
        }
    }

    private static void loadExpenses() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            expenses = (ArrayList<Expense>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // If file doesn't exist or error loading, continue with an empty list
            expenses = new ArrayList<>();
        }
    }

    private static void saveBudgets() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("budgets.dat"))) {
            oos.writeObject(budgets);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error saving budgets.");
        }
    }

    @SuppressWarnings("unchecked")
    private static void loadBudgets() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("budgets.dat"))) {
            budgets = (Map<String, Double>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // If file doesn't exist or error loading, continue with an empty map
            budgets = new HashMap<>();
        }
    }

    private static class ReminderTask extends TimerTask {
        @Override
        public void run() {
            System.out.println("\nChecking reminders...");
            Date currentDate = new Date();

            for (Expense expense : expenses) {
                // Check for due date reminders
                if (expense.dueDate != null && currentDate.after(expense.dueDate)) {
                    System.out.println("Reminder: Bill for category '" + expense.category + "' is due!");
                }

                // Check for budget limit reminders
                if (budgets.containsKey(expense.category) && expense.amount > budgets.get(expense.category)) {
                    System.out.println(
                            "Reminder: You are approaching the budget limit for category '" + expense.category + "'!");
                }
            }
        }
    }
}