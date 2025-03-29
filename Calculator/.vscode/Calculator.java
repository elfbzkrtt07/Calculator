import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * A simple GUI-based calculator using Java Swing.
 * Supports basic arithmetic operations and additional functions like square, square root, percentage, and inversion.
 * 
 * The calculator has a display and a grid of buttons that the user can click to perform calculations.
 * 
 * @author Elif Bozkurt
 * @author Metehan Kutay
 */
public class Calculator extends JFrame implements ActionListener {
    private JTextField display;
    private String input = "";
    private double num1 = 0;
    private Double num2 = null;
    private char operator = ' ';

    /**
     * Constructs the Calculator GUI.
     * Initializes the display and all calculator buttons with appropriate layout and styling.
     */
    public Calculator() {
        setTitle("Calculator");
        setSize(400, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        display = new JTextField();
        display.setFont(new Font("Monospace", Font.PLAIN, 40)); 
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setEditable(false);
        display.setBackground(Color.BLACK);
        display.setForeground(Color.WHITE);
        display.setPreferredSize(new Dimension(400, 80)); 
        add(display, BorderLayout.NORTH);
        
        String[] buttonLabels = {
            "%", "CE", "C", "←",
            "1/x", "x²", "√x", "÷",
            "7", "8", "9", "×",
            "4", "5", "6", "−",
            "1", "2", "3", "+",
            "+/−", "0", ",", "="
        };

        JPanel panel = new JPanel(new GridLayout(6, 4, 5, 5));
        panel.setBackground(Color.DARK_GRAY);

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Segoe UI", Font.BOLD, 20));
            button.setFocusPainted(false);
            button.setBackground(new Color(50, 50, 50));
            button.setForeground(Color.WHITE);
            button.addActionListener(this);

            if (label.equals("=")) {
                button.setBackground(new Color(255, 222, 89));
                button.setForeground(Color.BLACK);
            }

            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    /**
     * Handles button click events and performs the appropriate calculator logic.
     * 
     * @param e the ActionEvent triggered by a button press
     */
    public void actionPerformed(ActionEvent e) {
        String operation = e.getActionCommand();
        switch (operation) {
            case "C":
                input = "";
                num1 = 0;
                num2 = null;
                operator = ' ';
                break;
            case "CE":
                input = "";
                break;
            case "←":
                if (input.length() > 0)
                    input = input.substring(0, input.length() - 1);
                break;
            case "+/−":
                if (!input.isEmpty() && !input.startsWith("-"))
                    input = "-" + input;
                else if (input.startsWith("-"))
                    input = input.substring(1);
                break;
            case ",":
                if (!input.contains("."))
                    input += ".";
                break;
            case "+":
            case "−":
            case "×":
            case "÷":
                num1 = Double.parseDouble(input);
                operator = operation.charAt(0);
                input = "";
                break;
            case "=":
                if (num2 == null) {
                    try {
                        num2 = Double.parseDouble(input);
                    } catch (Exception ex) {
                        break;
                    }
                }
                switch (operator) {
                    case '+': num1 += num2; break;
                    case '−': num1 -= num2; break;
                    case '×': num1 *= num2; break;
                    case '÷': input = num2 != 0 ? String.valueOf(num1 /= num2) : "Error"; break;
                }
                if (!input.equals("Error")) {
                    input = String.valueOf(num1);
                }
                break;
            case "x²":
                input = String.valueOf(Math.pow(Double.parseDouble(input), 2));
                break;
            case "√x":
                input = String.valueOf(Math.sqrt(Double.parseDouble(input)));
                break;
            case "1/x":
                input = String.valueOf(1 / Double.parseDouble(input));
                break;
            case "%":
                input = String.valueOf(Double.parseDouble(input) / 100);
                break;
            default:
                input += operation;
                break;
        }

        display.setText(input);
    }

    /**
     * Main method to launch the calculator application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        new Calculator();
    }
}