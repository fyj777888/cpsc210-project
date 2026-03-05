import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import org.junit.jupiter.api.Test;

import model.Expense;
import model.ExpenseRecorder;
import model.FinanceApp;
import model.FinanceTracker;
import persistence.JsonReader;
import persistence.JsonWriter;

public class JsonWriterTest {
    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyFinanceApp() {
        try {
            FinanceApp fa = new FinanceApp(new ExpenseRecorder(), new FinanceTracker());
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyFinanceApp.json");
            writer.open();
            writer.write(fa);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyFinanceApp.json");
            fa = reader.read();
            assertTrue(fa.getExpenseRecorder().getExpenses().isEmpty());
            assertEquals(0, fa.getFinanceTracker().getValues());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralFinanceApp() {
        try {
            FinanceApp fa = new FinanceApp(new ExpenseRecorder(), new FinanceTracker());
            fa.addExpense(new Expense(20, "plumber", "other"));
            fa.addExpense(new Expense(10, "burger", "food"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralFinanceApp.json");
            writer.open();
            writer.write(fa);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralFinanceApp.json");
            fa = reader.read();
            assertTrue(fa.getExpenseRecorder().getExpenses().size() == 2);
            assertEquals(-30, fa.getFinanceTracker().getValues());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
