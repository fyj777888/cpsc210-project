package ui.gui;

import javax.swing.*;

import model.Expense;

import java.awt.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExpenseCategoryBarChartPanel extends JPanel {
    private Map<String, Double> categoryTotals;

    public ExpenseCategoryBarChartPanel(List<Expense> expenses) {
        categoryTotals = new LinkedHashMap<>();
        buildCategoryTotals(expenses);

        setPreferredSize(new Dimension(400, 300));
        setBackground(Color.WHITE);
    }

    private void buildCategoryTotals(List<Expense> expenses) {
        for (Expense e : expenses) {
            String category = e.getCategory();
            double amount = e.getExpenses();

            if (categoryTotals.containsKey(category)) {
                categoryTotals.put(category, categoryTotals.get(category) + amount);
            } else {
                categoryTotals.put(category, amount);
            }
        }
    }

    private void drawAxesAndTitle(Graphics2D g2, int panelWidth, int y0,
            int x0, int topBounds, int rightBounds) {
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Arial", Font.BOLD, 24));
        g2.drawString("Expense Categories", panelWidth / 2 - 100, 30);
        g2.drawLine(x0, y0, x0, topBounds);
        g2.drawLine(x0, y0, panelWidth - rightBounds, y0);
    }

    private double findMaxValue() {
        double maxValue = 0;
        for (double value : categoryTotals.values()) {
            if (value > maxValue) {
                maxValue = value;
            }
        }
        return maxValue;
    }

    private void drawBars(Graphics2D g2, int x0, int y0, int chartWidth,
            int chartHeight, double maxValue) {
        int numBars = categoryTotals.size();
        int barSpacing = 30;
        int totalSpacing = barSpacing * (numBars + 1);
        int barWidth = (chartWidth - totalSpacing) / numBars;
        int i = 0;
        for (String category : categoryTotals.keySet()) {
            double value = categoryTotals.get(category);
            int barX = x0 + barSpacing + i * (barWidth + barSpacing);
            int barHeight = (int) ((value / maxValue) * chartHeight);
            int barY = y0 - barHeight;
            g2.setColor(Color.BLUE);
            g2.fillRect(barX, barY, barWidth, barHeight);
            g2.setColor(Color.BLACK);
            g2.drawRect(barX, barY, barWidth, barHeight);
            g2.drawString(category, barX, y0 + 20);
            g2.drawString(Double.toString(value), barX, barY - 5);
            i++;
        }
    }

    private void drawAxisLabels(Graphics2D g2, int panelWidth,
            int panelHeight, int topBounds) {
        g2.setColor(Color.BLACK);
        g2.drawString("Amount", 20, topBounds - 10);
        g2.drawString("Category", panelWidth / 2 - 30, panelHeight - 20);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (categoryTotals.isEmpty()) {
            g.drawString("No expense data to display.", 50, 50);
            return;
        }
        Graphics2D g2 = (Graphics2D) g;
        int panelWidth = getWidth();
        int panelHeight = getHeight();
        int leftBounds = 100;
        int rightBounds = 100;
        int topBounds = 100;
        int bottomBounds = 100;
        int x0 = leftBounds;
        int y0 = panelHeight - bottomBounds;
        int chartWidth = panelWidth - leftBounds - rightBounds;
        int chartHeight = panelHeight - topBounds - bottomBounds;
        drawAxesAndTitle(g2, panelWidth, y0, x0, topBounds, rightBounds);
        double maxValue = findMaxValue();
        drawBars(g2, x0, y0, chartWidth, chartHeight, maxValue);
        drawAxisLabels(g2, panelWidth, panelHeight, topBounds);
    }

}