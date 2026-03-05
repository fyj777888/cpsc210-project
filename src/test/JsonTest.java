import static org.junit.jupiter.api.Assertions.assertEquals;

import model.ExpenseRecorder;
import model.FinanceApp;
import model.FinanceTracker;

public class JsonTest {
    protected void checkFinanceApp(ExpenseRecorder e, FinanceTracker f,FinanceApp fa) { 
        assertEquals(e, fa.getExpenseRecorder());
        assertEquals(f, fa.getFinanceTracker());
    }
}
