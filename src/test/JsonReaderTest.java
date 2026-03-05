import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.Expense;
import model.FinanceApp;
import persistence.JsonReader;

public class JsonReaderTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            FinanceApp fa = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyFinanceApp() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyFinanceApp.json");
        try {
            FinanceApp fa = reader.read();
            assertTrue(fa.getExpenseRecorder().getExpenses().isEmpty());
            assertEquals(0, fa.getFinanceTracker().getValues());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralFinanceApp() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralFinanceApp.json");
        try {
            FinanceApp fa = reader.read();
            assertTrue(fa.getExpenseRecorder().getExpenses().isEmpty());
            fa.getExpenseRecorder().addExpense(new Expense(20, "plumber", "other"));
            List<Expense> expenses = fa.getExpenseRecorder().getExpenses();
            assertEquals(1, expenses.size());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
