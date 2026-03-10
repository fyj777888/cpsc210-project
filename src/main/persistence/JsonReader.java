package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Expense;
import model.ExpenseRecorder;
import model.FinanceApp;
import model.FinanceTracker;

// Represents a reader that reads FinanceApp from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads FinanceApp from file and returns it;
    // throws IOException if an error occurs reading data from file
    public FinanceApp read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseFinanceApp(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses FinanceApp from JSON object and returns it
    private FinanceApp parseFinanceApp(JSONObject jsonObject) {
        ExpenseRecorder recorder = new ExpenseRecorder();
        FinanceTracker tracker = new FinanceTracker();
        parseTracker(tracker, jsonObject);
        FinanceApp fa = new FinanceApp();
        fa.setFinanceApp(recorder, tracker);
        addExpenses(fa, jsonObject);
        return fa;
    }

    // MODIFIES: tracker
    // EFFECTS: parses tracker state from JSON and restores values/loans
    private void parseTracker(FinanceTracker tracker, JSONObject jsonObject) {
        if (!jsonObject.has("tracker")) {
            return;
        }

        JSONObject trackerJson = jsonObject.getJSONObject("tracker");
        double values = trackerJson.getDouble("values");
        double loans = trackerJson.getDouble("loans");

        tracker.addValues(values);
        if (loans != 0) {
            tracker.borrowMoney(loans);
            tracker.decreaseValues(loans);
        }
    }


    // MODIFIES: fa
    // EFFECTS: parses FinanceApp from JSON object and adds them to FinanceApp
    private void addExpenses(FinanceApp fa, JSONObject jsonObject) {
        if (!jsonObject.has("expenses")) {
            return;
        }

        JSONArray jsonArray = jsonObject.getJSONArray("expenses");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addExpense(fa, nextThingy);
        }
    }

    // MODIFIES: fa
    // EFFECTS: parses FinanceApp from JSON object and adds it to FinanceApp
    private void addExpense(FinanceApp fa, JSONObject jsonObject) {
        double values = jsonObject.getDouble("values");
        String purpose = jsonObject.getString("purpose");
        String category = jsonObject.getString("category");
        Expense expense = new Expense(values, purpose, category);
        fa.getExpenseRecorder().addExpense(expense);
    }
}
