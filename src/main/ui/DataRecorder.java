package ui;

import java.io.FileNotFoundException;
import java.io.IOException;

import model.FinanceApp;
import persistence.JsonReader;
import persistence.JsonWriter;

public class DataRecorder {
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/financeApp.json";

    public DataRecorder() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // EFFECTS: saves the FinanceApp to file
    public void saveFinanceApp(FinanceApp financeApp) {
        try {
            jsonWriter.open();
            jsonWriter.write(financeApp);
            jsonWriter.close();
            System.out.println("Saved " + "financeApp" + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // EFFECTS: loads FinanceApp from file
    public FinanceApp loadFinanceApp() {
        try {
            FinanceApp financeApp = jsonReader.read();
            System.out.println("Loaded " + "financeApp" + " from " + JSON_STORE);
            return financeApp;
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
            return null;
        }
    }
}