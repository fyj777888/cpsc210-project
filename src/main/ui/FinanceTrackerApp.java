package ui;

import java.util.Scanner;

import model.Expense;
import model.ExpenseRecorder;
import model.FinanceTracker;

public class FinanceTrackerApp {
    Scanner sc;
    int option;
    String category;
    ExpenseRecorder expenseRecorder = new ExpenseRecorder();
    FinanceTracker financeTracker = new FinanceTracker();

    public FinanceTrackerApp() {
        sc = new Scanner(System.in);

        boolean keepGoing = true;
        while (keepGoing) {
            printOptions();
            if (option == 0) {
                keepGoing = false;
            } else {
                makeOptions(option);
            }
        }
        System.out.println("Goodbye!");
    }

    private void printOptions() {
        while (true) {
            System.out.println();
            System.out.println("1) Add an expense");
            System.out.println("2) List all expenses");
            System.out.println("3) Show balance and loan balance");
            System.out.println("4) Borrow a loan");
            System.out.println("5) Repay a loan");
            System.out.println("6) Show total spent (all / by category)");
            System.out.println("0) Quit");
            sc = new Scanner(System.in);
            if (sc.hasNextInt()) {
                option = sc.nextInt();
                if (option >= 0 && option <= 6) {
                    break;
                } else {
                    System.out.println("Option must be 0-6.");
                }
            } else {
                System.out.println("Please input correct choice");
                sc.nextLine();
            }
        }

    }

    private void makeOptions(int option) {
        if (option == 1) {
            addExpense();
        } else if (option == 2) {
            System.out.println(expenseRecorder.getExpenses());
        } else if (option == 3) {
            System.out.println("Balance:" + financeTracker.getValues());
            System.out.println("Loans:" + financeTracker.getLoans());
        } else if (option == 4) {
            borrowLoan();
        } else if (option == 5) {
            repayLoan();
        } else if (option == 6) {
            listSpent();
        }
    }

    public void addExpense() {
        int values;
        String purpose;
        while (true) {
            System.out.println("How much you spent");
            if (sc.hasNextInt()) {
                values = sc.nextInt();
                financeTracker.decreaseValues(values);
                break;
            } else {
                System.out.println("Please input correct values");
                sc.nextLine();
            }
        }
        System.out.println("What's your purpose");
        purpose = sc.next();
        System.out.println("Choose one of the category:1) food 2) rental 3) entertainment 4) others");
        addCategory();
        expenseRecorder.addExpense(new Expense(values, purpose, category));
    }

    public void addCategory() {
        while (true) {
            if (sc.hasNextInt()) {
                int choice = sc.nextInt();
                if (choice >= 1 && choice <= 4) {
                    if (choice == 1) {
                        category = "food";
                    } else if (choice == 2) {
                        category = "rental";
                    } else if (choice == 3) {
                        category = "entertainment";
                    } else {
                        category = "others";
                    }
                    break;
                } else {
                    System.out.println("Please input correct choice");
                    sc.nextLine();
                }
            }
        }
    }

    public void listSpent() {
        System.out.println("all / category");
        int expense = 0;
        while (true) {
            String type = sc.next();
            if (type.equals("all")) {
                for (Expense e : expenseRecorder.getExpenses()) {
                    expense = expense + e.getExpenses();
                }
                System.out.println(expense);
                break;
            } else if (type.equals("category")) {
                spentCategory();
            }
            for (Expense e : expenseRecorder.getExpenses()) {
                if (e.getCategory().equals(new String(category))) {
                    expense = expense + e.getExpenses();
                }
            }
        }

    }

    public void spentCategory() {
        System.out.println("Choose one of the category:1) food 2) rental 3) entertainment 4) others");
        while (true) {
            if (sc.hasNextInt()) {
                int choice = sc.nextInt();
                if (choice >= 1 && choice <= 4) {
                    if (choice == 1) {
                        category = "food";
                    } else if (choice == 2) {
                        category = "rental";
                    } else if (choice == 3) {
                        category = "entertainment";
                    } else {
                        category = "others";
                    }
                    break;
                } else {
                    System.out.println("Please input correct choice");
                    sc.nextLine();
                }
            }
        }
    }

    public void repayLoan() {
        System.out.println("How much you want to repay:");
        while (true) {
            if (sc.hasNextInt()) {
                int repays = sc.nextInt();
                if (0 < repays && repays <= financeTracker.getLoans()) {
                    financeTracker.repayMoney(repays);
                    break;
                } else {
                    System.out.println("Please input an integer between 1 and loans");
                    sc.nextLine();
                }
            }

        }
    }

    public void borrowLoan() {
        System.out.println("How much you want to borrow:");
        while (true) {
            if (sc.hasNextInt()) {
                int loans = sc.nextInt();
                if (0 < loans && loans <= 1000000) {
                    financeTracker.borrowMoney(loans);
                    break;
                } else {
                    System.out.println("Please input an integer between 1 and 1000000");
                    sc.nextLine();
                }
            }
        }
    }
}