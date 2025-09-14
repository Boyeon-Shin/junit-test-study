package ch06.click;

import javax.swing.*;

public class ClickExample {
    private final JButton button;
    private final JLabel resultLabel;

    public ClickExample() {
        this.button = new JButton("Click Me!");
        this.resultLabel = new JLabel("Waiting...");

        // JS의 window.onload → 초기 상태
        resultLabel.setText("Document Loaded");

        // JS의 addEventListener("click", cb)
        button.addActionListener(e -> resultLabel.setText("Clicked!"));
    }

    public JButton getButton() {
        return button;
    }

    public JLabel getResultLabel() {
        return resultLabel;
    }
}