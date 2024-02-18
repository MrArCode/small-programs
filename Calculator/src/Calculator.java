import javax.swing.*;
import java.awt.*;

public class Calculator {
    private JLabel resultLabel;
    private boolean isOperation = false;
    private boolean isSecondNumber = false;
    private double firstNumber = 0;
    private double secondNumber = 0;
    private double memoryNumber = 0;
    private char operation = ' ';

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.createAndShowGUI();
    }

    private void createAndShowGUI() {

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

    public void handleButtonClick(String button) {
        switch (button) {
            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
                if (isOperation == true) {
                    secondNumber = secondNumber * 10 + Double.parseDouble(button);
                    resultLabel.setText(String.valueOf(secondNumber));
                    isSecondNumber = true;
                    memoryNumber = secondNumber;
                } else {
                    firstNumber = firstNumber * 10 + Double.parseDouble(button);
                    resultLabel.setText(String.valueOf(firstNumber));
                }
                break;
            case "+":
            case "-":
            case "*":
            case "÷":
                memoryNumber = firstNumber;
                isOperation = true;
                operation = button.charAt(0);
                break;
            case "=":
                if (isOperation == true && isSecondNumber == false) {
                    switch (operation) {
                        case '+':
                            firstNumber += memoryNumber;
                            break;
                        case '-':
                            firstNumber -= memoryNumber;
                            break;
                        case '*':
                            firstNumber *= memoryNumber;
                            break;
                        case '÷':
                            if (memoryNumber != 0) {
                                firstNumber /= memoryNumber;
                            } else {
                                resultLabel.setText("ERROR");// ================================================
                            }
                            break;
                        default:
                            break;
                    }
                    resultLabel.setText(String.valueOf(firstNumber));
                } else if (isOperation == true && isSecondNumber == true) {
                    switch (operation) {
                        case '+':
                            firstNumber += secondNumber;
                            secondNumber = 0;
                            resultLabel.setText(String.valueOf(firstNumber));
                            isSecondNumber = false;
                            isOperation = false;
                            break;
                        case '-':
                            firstNumber -= secondNumber;
                            secondNumber = 0;
                            resultLabel.setText(String.valueOf(firstNumber));
                            isSecondNumber = false;
                            isOperation = false;
                            break;
                        case '*':
                            firstNumber *= secondNumber;
                            secondNumber = 0;
                            resultLabel.setText(String.valueOf(firstNumber));
                            isSecondNumber = false;
                            isOperation = false;
                            break;
                        case '÷':
                            if (secondNumber != 0) {
                                firstNumber /= secondNumber;
                                secondNumber = 0;
                                resultLabel.setText(String.valueOf(firstNumber));
                                isSecondNumber = false;
                                isOperation = false;
                            } else {
                                resultLabel.setText("ERROR");// ================================================
                            }
                            break;
                        default:
                            break;
                    }

                } else if (isOperation == false && isSecondNumber == false) {
                    switch (operation) {
                        case '+':
                            firstNumber += memoryNumber;
                            resultLabel.setText(String.valueOf(firstNumber));
                            break;
                        case '-':
                            firstNumber -= memoryNumber;
                            resultLabel.setText(String.valueOf(firstNumber));
                            break;
                        case '*':
                            firstNumber *= memoryNumber;
                            resultLabel.setText(String.valueOf(firstNumber));
                            break;
                        case '÷':
                            if (memoryNumber != 0) {
                                firstNumber /= memoryNumber;
                                resultLabel.setText(String.valueOf(firstNumber));
                            } else {
                                resultLabel.setText("ERROR");// ================================================
                            }
                            break;
                        default:
                            break;
                    }
                }
                break;

            case "C":
                isOperation = false;
                isSecondNumber = false;
                firstNumber = 0;
                secondNumber = 0;
                memoryNumber = 0;
                operation = ' ';
                resultLabel.setText(String.valueOf(firstNumber));
                break;

            case "%":
                if (isSecondNumber) {
                    secondNumber *= 0.01;
                    memoryNumber = secondNumber;
                    resultLabel.setText(String.valueOf(secondNumber));
                } else {
                    firstNumber *= 0.01;
                    resultLabel.setText(String.valueOf(firstNumber));

                }
                break;

            case "x^2":
                if (isSecondNumber) {
                    secondNumber = secondNumber * secondNumber;
                    memoryNumber = secondNumber;
                    resultLabel.setText(String.valueOf(secondNumber));
                } else {
                    firstNumber = firstNumber * firstNumber;
                    resultLabel.setText(String.valueOf(firstNumber));
                }
                break;

            case "√x":
                if (isSecondNumber) {
                    secondNumber = Math.sqrt(secondNumber);
                    memoryNumber = secondNumber;
                    resultLabel.setText(String.valueOf(secondNumber));
                } else {
                    firstNumber = Math.sqrt(firstNumber);
                    resultLabel.setText(String.valueOf(firstNumber));
                }
                break;

            default:
                break;
        }
    }

    public void exponentiationOperetion() {

    }
}
