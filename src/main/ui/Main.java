package ui;

import model.PrintEventLog;
import ui.gui.MyFrame;

public class Main {
    public static void main(String[] args) throws Exception {
        new MyFrame();
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                PrintEventLog.printEventLog();
            }
        }));
    }
}
