package ui.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.FinanceApp;

public class MyFrame extends JFrame {
    private JPanel currentPanel;
    
    public MyFrame() {
        super("Finance Tracker App");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900, 675);
        this.setLayout(null);
        MainMenuPanel mainMenuPanel = new MainMenuPanel(this);
        mainMenuPanel.setBounds(0, 0, 900, 100);
        this.add(mainMenuPanel);
        this.setVisible(true);
    }

    public void showPanel(JPanel panel) {
        if (currentPanel != null) {
            this.remove(currentPanel);
        }
        currentPanel = panel;
        currentPanel.setBounds(0, 100, 900, 650);
        this.add(currentPanel);
        this.revalidate();
        this.repaint();
    }

}
