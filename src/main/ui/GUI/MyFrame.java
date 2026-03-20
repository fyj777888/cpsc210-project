package ui.GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.FinanceApp;

public class MyFrame extends JFrame {
    private JPanel currentPanel;
    
    public MyFrame() {
        super("Finance Tracker App");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1600, 1200);
        this.setLayout(null);
        MainMenuPanel mainMenuPanel = new MainMenuPanel(this);
        mainMenuPanel.setBounds(0, 0, 1600, 150);
        this.add(mainMenuPanel);
        this.setVisible(true);
    }

    public void showPanel(JPanel panel) {
        if (currentPanel != null) {
            this.remove(currentPanel);
        }
        currentPanel = panel;
        currentPanel.setBounds(0, 150, 1600, 1050);
        this.add(currentPanel);
        this.revalidate();
        this.repaint();
    }

}
