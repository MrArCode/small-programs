import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Calculator {
    private JLabel resultLabel;
    private StringBuilder currentInput;
    private double result;
    private char operation;

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.createAndShowGUI();
    }

    private void createAndShowGUI() {
        currentInput = new StringBuilder();
        result = 0;
        operation = ' ';

        // Main window
        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 700);

        // Icon setup
        ImageIcon icon = new ImageIcon("src\\icon.png");
        frame.setIconImage(icon.getImage());

        // Colors
        Color backgroundColor = new Color(235, 245, 251);
        Color buttonColor = new Color(207, 226, 243);
        Color operatorButtonColor = new Color(97, 145, 197);
        Color textColor = new Color(25, 25, 112);

        JPanel resultPanel = new JPanel();
        resultPanel.setBackground(backgroundColor);
        resultPanel.setLayout(new BorderLayout());
        resultPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        resultLabel = new JLabel("0");
        resultLabel.setFont(new Font("SansSerif", Font.BOLD, 50));
        resultLabel.setForeground(textColor);

        resultPanel.add(BorderLayout.LINE_END, resultLabel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 4, 10, 10));
        buttonPanel.setBackground(backgroundColor);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] buttonLabels = {
                "%", "CE", "C", "⌫",
                "1/x", "x^2", "√x", "÷",
                "7", "8", "9", "*",
                "4", "5", "6", "-",
                "1", "2", "3", "+",
                "+/-", "0", ",", "="
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setPreferredSize(new Dimension(40, 60));
            button.setFont(new Font("SansSerif", Font.BOLD, 25));
            button.setForeground(textColor);

            button.addActionListener(e -> {
                handleButtonClick(button.getText());

                // Add visual feedback with a brief color change
                button.setBackground(getComplementaryColor(button.getBackground()));
                Timer timer = new Timer(100, evt -> {
                    button.setBackground(label.matches("[0-9]")
                            ? buttonColor
                            : operatorButtonColor);
                    ((Timer) evt.getSource()).stop();
                });
                timer.setRepeats(false);
                timer.start();
            });

            if (label.matches("[0-9]")) {
                button.setBackground(buttonColor);
            } else {
                button.setBackground(operatorButtonColor);
            }

            button.setFocusPainted(false);
            button.setBorderPainted(false);
            button.setContentAreaFilled(false);
            button.setOpaque(true);
            button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            buttonPanel.add(button);
        }

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BorderLayout());
        northPanel.setBackground(backgroundColor);
        northPanel.add(resultPanel, BorderLayout.SOUTH);

        frame.getContentPane().add(BorderLayout.SOUTH, buttonPanel);
        frame.add(BorderLayout.CENTER, northPanel);

        frame.setVisible(true);
    }

    private Color getComplementaryColor(Color originalColor) {
        // Slightly lighten the button color for a visual effect when pressed
        float[] hsbVals = Color.RGBtoHSB(originalColor.getRed(), originalColor.getGreen(), originalColor.getBlue(),
                null);
        return Color.getHSBColor(hsbVals[0], hsbVals[1] * 0.8f, Math.min(1.0f, hsbVals[2] * 1.2f));
    }

    // ===============================================================================================================================================================================================================
    private void handleButtonClick(String button) {
        switch (button) {
            case "+":
            case "-":
            case "*":
            case "÷":

                performOperation();
                operation = button.charAt(0);
                break;

            case "=":
                performOperation();
                operation = ' ';
                break;

            case "+/-":
                changeSign();
                break;

            default:
                currentInput.append(button);
                resultLabel.setText(currentInput.toString());
                break;
        }
    }

    private void performOperation() {
        if (currentInput.length() > 0) {
            double currentNumber = Double.parseDouble(currentInput.toString());
            switch (operation) {
                case '+':
                    result += currentNumber;
                    break;
                case '-':
                    result -= currentNumber;
                    break;
                case '*':
                    result *= currentNumber;
                    break;
                case '÷':
                    if (currentNumber != 0) {
                        result /= currentNumber;
                    } else {
                        resultLabel.setText("Error");
                        resetCalculator();
                        return;
                    }
                    break;
                default:
                    result = currentNumber;
            }
            resultLabel.setText(String.valueOf(result));
            resetCurrentInput();
        }
    }

    private void changeSign() {
        if (currentInput.length() > 0) {
            double currentNumber = Double.parseDouble(currentInput.toString());
            currentNumber = -currentNumber;
            resultLabel.setText(String.valueOf(currentNumber));
            resetCurrentInput();
        }
    }

    private void resetCurrentInput() {
        currentInput.setLength(0);
    }

    private void resetCalculator() {
        result = 0;
        resetCurrentInput();
        operation = ' ';
    }
}
