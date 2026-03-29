package ui.gui;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.Event;
import model.EventLog;

public class EventLogPanel extends JPanel {
    private JTextArea textArea;

    public EventLogPanel() {
        setLayout(null);
        
        JLabel titleLabel = new JLabel("Event Log");
        titleLabel.setBounds(50, 20, 300, 40);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel);
        
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Arial", Font.PLAIN, 12));
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(50, 70, 300, 500);
        add(scrollPane);
        displayEventLog();
    }

    private void displayEventLog() {
        StringBuilder sb = new StringBuilder();
        EventLog eventLog = EventLog.getInstance();
        
        for (Event event : eventLog) {  
            sb.append(event.toString()).append("\n");
            sb.append("-----------------------------------\n");
        }
        
        if (sb.length() == 0) {
            sb.append("No events logged yet.");
        }
        
        textArea.setText(sb.toString());
    }
}
