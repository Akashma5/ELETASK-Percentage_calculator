import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PercentageCalculator {
    // GUI components
    private JFrame frame;
    private JTextField valueField;
    private JTextField percentageField;
    private JComboBox<String> calculationTypeComboBox;
    private JButton calculateButton;
    private JLabel resultLabel;

    // Calculation types
    private static final String[] CALCULATION_TYPES = {
            "Calculate percentage",
            "Calculate percentage increase",
            "Calculate percentage decrease",
            "Find whole given part and percentage"
    };

    public PercentageCalculator() {
        createGUI();
    }

    private void createGUI() {
        // Set look and feel to system default
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        frame = new JFrame("Percentage Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.getContentPane().setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Value field
        JLabel valueLabel = new JLabel("Value:");
        valueField = new JTextField(10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(valueLabel, gbc);
        gbc.gridx = 1;
        frame.add(valueField, gbc);

        // Percentage field
        JLabel percentageLabel = new JLabel("Percentage:");
        percentageField = new JTextField(10);
        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.add(percentageLabel, gbc);
        gbc.gridx = 1;
        frame.add(percentageField, gbc);

        // Calculation type combo box
        JLabel calculationTypeLabel = new JLabel("Calculation type:");
        calculationTypeComboBox = new JComboBox<>(CALCULATION_TYPES);
        gbc.gridx = 0;
        gbc.gridy = 2;
        frame.add(calculationTypeLabel, gbc);
        gbc.gridx = 1;
        frame.add(calculationTypeComboBox, gbc);

        // Calculate button
        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new CalculateButtonListener());
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        frame.add(calculateButton, gbc);

        // Result label
        resultLabel = new JLabel("Result:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        frame.add(resultLabel, gbc);

        frame.pack();
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }

    private class CalculateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double value = Double.parseDouble(valueField.getText());
                double percentage = Double.parseDouble(percentageField.getText());
                int calculationTypeIndex = calculationTypeComboBox.getSelectedIndex();

                double result = 0;
                switch (calculationTypeIndex) {
                    case 0: // Calculate percentage
                        result = (value / 100) * percentage;
                        break;
                    case 1: // Calculate percentage increase
                        result = value + (value * (percentage / 100));
                        break;
                    case 2: // Calculate percentage decrease
                        result = value - (value * (percentage / 100));
                        break;
                    case 3: // Find whole given part and percentage
                        result = (value / percentage) * 100;
                        break;
                }

                resultLabel.setText("Result: " + String.format("%.2f", result));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input. Please enter valid numbers.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PercentageCalculator();
            }
        });
    }
}
