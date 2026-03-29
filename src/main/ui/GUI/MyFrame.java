package ui.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyFrame extends JFrame {
    private JPanel currentPanel;
    
    public MyFrame() {
        super("Finance Tracker App");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 750);
        this.setLayout(null);
        MainMenuPanel mainMenuPanel = new MainMenuPanel(this);
        mainMenuPanel.setBounds(0, 0, 1000, 100);
        this.add(mainMenuPanel);
        this.setVisible(true);
    }

    public void showPanel(JPanel panel) {
        if (currentPanel != null) {
            this.remove(currentPanel);
        }
        currentPanel = panel;
        currentPanel.setBounds(0, 100, 1000, 650);
        this.add(currentPanel);
        this.revalidate();
        this.repaint();
    }

}
