package ui;

import java.util.Scanner;

import model.Expense;
import model.FinanceApp;

public class FinanceTrackerApp {
    private Scanner sc;
    private int option;
    FinanceApp financeApp = new FinanceApp();
    private DataRecorder dataRecorder;

    public FinanceTrackerApp() {
        sc = new Scanner(System.in);
        dataRecorder = new DataRecorder();
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
            System.out.println("7) Save financeApp");
            System.out.println("8) Load financeApp");
            System.out.println("0) Quit");
            if (sc.hasNextInt()) {
                option = sc.nextInt();
                if (option >= 0 && option <= 8) {
                    break;
                } else {
                    System.out.println("Option must be 0-8.");
                }
            } else {
                System.out.println("Please input correct choice");
                sc.nextLine();
            }
        }

    }

    @SuppressWarnings("methodlength")
    private void makeOptions(int option) {
        switch (option) {
            case 1:
                addExpense();
                return;
            case 2:
                listAllExpenses();
                return;
            case 3:
                showBalanceAndLoans();
                return;
            case 4:
                borrowLoan();
                return;
            case 5:
                repayLoan();
                return;
            case 6:
                listSpent();
                return;
            case 7:
                saveData();
                return;
            case 8:
                loadData();
                return;
            default:
                return;
        }
    }

    public void addExpense() {
        double values;
        String purpose;
        String category;
        while (true) {
            System.out.println("How much you spent");
            if (sc.hasNextDouble()) {
                values = sc.nextDouble();
                break;
            } else {
                System.out.println("Please input correct values");
                sc.nextLine();
            }
        }
        System.out.println("What's your purpose");
        purpose = sc.next();
        category = chooseCategory();
        financeApp.addExpense(new Expense(values, purpose, category));
    }

    public void listAllExpenses() {
        for (Expense e : financeApp.getExpenseRecorder().getExpenses()) {
            System.out.println("Values:" + e.getExpenses());
            System.out.println("Purpose:" + e.getPurpose());
            System.out.println("Category:" + e.getCategory());
        }
    }

    public void showBalanceAndLoans() {
        System.out.println("Balance:" + financeApp.getFinanceTracker().getValues());
        System.out.println("Loans:" + financeApp.getFinanceTracker().getLoans());
    }

    @SuppressWarnings("methodlength")
    public String chooseCategory() {
        String category;
        System.out.println("Choose one of the category:1) food 2) rental 3) entertainment 4) others");
        while (true) {
            if (sc.hasNextInt()) {
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        category = "food";
                        return category;
                    case 2:
                        category = "rental";
                        return category;
                    case 3:
                        category = "entertainment";
                        return category;
                    case 4:
                        category = "others";
                        return category;
                    default:
                        System.out.println("Please input correct choice");
                        sc.nextLine();
                }
            } else {
                System.out.println("Please input correct choice");
                sc.next();
            }
        }
    }

    @SuppressWarnings("methodlength")
    public void listSpent() {
        String category;
        System.out.println("all / category");
        double expense = 0;
        while (true) {
            String type = sc.next();
            if (type.equals("all")) {
                for (Expense e : financeApp.getExpenseRecorder().getExpenses()) {
                    expense = expense + e.getExpenses();
                }
                System.out.println(expense);
                break;
            } else if (type.equals("category")) {
                category = chooseCategory();
                for (Expense e : financeApp.getExpenseRecorder().getExpenses()) {
                    if (e.getCategory().equals(category)) {
                        expense = expense + e.getExpenses();
                    }
                }
                System.out.println(expense);
                break;
            } else {
                System.out.println("Please input \"all\" or \"category\"");
            }
        }
    }

    public void repayLoan() {
        if (financeApp.getFinanceTracker().getLoans() == 0) {
            System.out.println("You have no loans to repay.");
            return;
        }
        System.out.println("How much you want to repay:");
        while (true) {
            if (sc.hasNextDouble()) {
                double repays = sc.nextDouble();
                if (0 < repays && repays <= financeApp.getFinanceTracker().getLoans()) {
                    financeApp.getFinanceTracker().repayMoney(repays);
                    break;
                } else {
                    System.out.println("Please input an number between 1 and loans");
                    sc.nextLine();
                }
            } else {
                System.out.println("Please input a number");
                sc.next();
            }

        }
    }

    public void borrowLoan() {
        System.out.println("How much you want to borrow:");
        while (true) {
            if (sc.hasNextDouble()) {
                double loans = sc.nextDouble();
                if (0 < loans && loans <= 1000000) {
                    financeApp.getFinanceTracker().borrowMoney(loans);
                    break;
                } else {
                    System.out.println("Please input an integer between 1 and 1000000");
                    sc.nextLine();
                }
            } else {
                System.out.println("Please input a number");
                sc.next();
            }
        }
    }

    public void saveData() {
        dataRecorder.saveFinanceApp(financeApp);
    }

    public void loadData() {
        FinanceApp loaded = dataRecorder.loadFinanceApp();
        if (loaded == null) {
            System.out.println("Load failed: loaded == null");           
        } else {
            financeApp = loaded;
            System.out.println("Load successful!");
        }
    }

}
