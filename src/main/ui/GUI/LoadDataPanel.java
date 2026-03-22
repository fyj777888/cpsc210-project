package ui.GUI;

import java.awt.TextArea;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import model.FinanceApp;
import ui.DataRecorder;

public class LoadDataPanel extends JPanel {
    private MainMenuPanel mainMenuPanel;
    private JTextArea textArea;

    public LoadDataPanel(MainMenuPanel mainMenuPanel) {
        this.mainMenuPanel = mainMenuPanel;
        FinanceApp loaded = mainMenuPanel.getDataRecorder().loadFinanceApp();
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setBounds(50, 200, 400, 200);
        if (loaded == null) {
            textArea.setText("Load failed: loaded == null");
        } else {
            mainMenuPanel.setFinanceApp(loaded);
            textArea.setText("Load successful!");
        }
    }
}
