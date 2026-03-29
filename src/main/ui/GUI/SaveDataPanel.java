package ui.gui;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class SaveDataPanel extends JPanel {
    private MainMenuPanel mainMenuPanel;
    private JTextArea textArea;

    public SaveDataPanel(MainMenuPanel mainMenuPanel) {
        this.mainMenuPanel = mainMenuPanel;
        mainMenuPanel.getDataRecorder().saveFinanceApp(mainMenuPanel.getFinanceApp());
        setLayout(null);
        textArea = new JTextArea();
        textArea.setBounds(50, 200, 400, 200);
        textArea.setEditable(false);
        textArea.setText("Save data succesfully!");
    }

}
