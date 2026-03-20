import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import model.FinanceTracker;

public class FinanceTrackerTest {
    FinanceTracker financeTracker1;
    FinanceTracker financeTracker2;

    @BeforeEach
    void runBefore() {
        financeTracker1 = new FinanceTracker();
        financeTracker2 = new FinanceTracker();
    }

    @Test
    void updateValuesAndLoansTest() {
        assertEquals(0, financeTracker1.getValues());
        assertEquals(0, financeTracker1.getLoans());
        financeTracker1.addValues(100);
        assertEquals(100, financeTracker1.getValues());
        assertEquals(0, financeTracker1.getLoans());
        financeTracker1.borrowMoney(50);
        assertEquals(150, financeTracker1.getValues());
        assertEquals(50, financeTracker1.getLoans());
        financeTracker1.decreaseValues(20);
        assertEquals(130, financeTracker1.getValues());
        assertEquals(50, financeTracker1.getLoans());
        financeTracker1.repayMoney(30);
        assertEquals(100, financeTracker1.getValues());
        assertEquals(20, financeTracker1.getLoans());
    }

    @Test
    void twoTrackersTest() {
        financeTracker1.addValues(10);
        financeTracker1.borrowMoney(20);
        assertEquals(30, financeTracker1.getValues());
        assertEquals(20, financeTracker1.getLoans());
        assertEquals(0, financeTracker2.getValues());
        assertEquals(0, financeTracker2.getLoans());
    }

    @Test
    void setLoansAndValuesTest() {
        financeTracker1.setLoans(100);
        financeTracker1.setValues(200);
        assertEquals(200, financeTracker1.getValues());
        assertEquals(100, financeTracker1.getLoans());
    }
}
