import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel resultPanel = new JPanel();
        resultPanel.setBackground(new Color(250, 250, 250)); 
        resultPanel.setLayout(new BorderLayout());
        resultPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        resultLabel = new JLabel("");
        resultLabel.setFont(new Font("SansSerif", Font.PLAIN, 30));
        resultLabel.setForeground(Color.BLACK);

        resultPanel.add(BorderLayout.LINE_END, resultLabel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 10, 10));
        buttonPanel.setBackground(new Color(232, 232, 232)); 
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "+/-", "0", "=", "+"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setPreferredSize(new Dimension(60, 60));
            button.setFont(new Font("SansSerif", Font.PLAIN, 20));

            button.addActionListener(e -> {
                handleButtonClick(button.getText());

                // Add visual feedback with a brief color change
                button.setBackground(getComplementaryGray(button.getBackground()));
                Timer timer = new Timer(100, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        button.setBackground(Character.isDigit(button.getText().charAt(0))
                                ? new Color(255, 255, 255) // White for digits
                                : new Color(255, 193, 7)); // Amber for operators
                        ((Timer) evt.getSource()).stop();
                    }
                });
                timer.setRepeats(false);
                timer.start();
            });

            if (Character.isDigit(label.charAt(0))) {
                button.setBackground(new Color(255, 255, 255)); // White
            } else {
                button.setBackground(new Color(255, 193, 7)); // Amber
            }

            // Add rounded corners
            button.setFocusPainted(false);
            button.setBorderPainted(false);
            button.setContentAreaFilled(false);
            button.setOpaque(true);
            button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            buttonPanel.add(button);
        }

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BorderLayout());
        northPanel.setBackground(new Color(250, 250, 250)); // Light Gray
        northPanel.add(resultPanel, BorderLayout.SOUTH);

        frame.getContentPane().add(BorderLayout.SOUTH, buttonPanel);
        frame.add(BorderLayout.CENTER, northPanel);

        frame.setSize(400, 600);
        frame.setVisible(true);
    }

    private Color getComplementaryGray(Color originalColor) {
        int grayValue = (int) (0.7 * originalColor.getRed() + 0.21 * originalColor.getGreen()
                + 0.07 * originalColor.getBlue());
        return new Color(grayValue, grayValue, grayValue);
    }

    private void handleButtonClick(String button) {
        switch (button) {
            case "+":
            case "-":
            case "*":
            case "/":
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
                case '/':
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
