import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Expense;

public class ExpenseTest {
    Expense expense1;
    Expense expense2;
@BeforeEach
void runBefore(){
    expense1 = new Expense(100,"cake","food");
    expense2 = new Expense(200,"ski","entertainment");
}

@Test
void getExpensesTest(){
    assertEquals(expense1.getExpenses(), 100);
    assertEquals(expense2.getExpenses(), 200);
}

@Test
void getPurposeTest(){
    assertEquals(expense1.getPurpose(), "cake");
    assertEquals(expense2.getPurpose(), "ski");
}


@Test
void getCategoryTest(){
    assertEquals(expense1.getCategory(), "food");
    assertEquals(expense2.getCategory(), "entertainment");
}


}
