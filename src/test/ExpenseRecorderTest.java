import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Expense;
import model.ExpenseRecorder;

public class ExpenseRecorderTest {
    private ExpenseRecorder recorder;

    @BeforeEach
    void runBefore() {
        recorder = new ExpenseRecorder();
    }

    @Test
    void addAndGetExpensesTest() {
        assertEquals(0, recorder.getExpenses().size());

        Expense e1 = new Expense(10, "Lunch", "Food");
        Expense e2 = new Expense(25, "Bus", "Transport");

        recorder.addExpense(e1);
        assertEquals(1, recorder.getExpenses().size());
        assertEquals(e1, recorder.getExpenses().get(0));

        recorder.addExpense(e2);
        assertEquals(2, recorder.getExpenses().size());
        assertEquals(e2, recorder.getExpenses().get(1));
    }

    @Test
    void getExpensesSameListReferenceTest() {

        recorder.getExpenses().add(new Expense(5, "Snack", "Food"));
        assertEquals(1, recorder.getExpenses().size());
    }
}
