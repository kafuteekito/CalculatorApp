package org.example.calculatorapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TextField;

import java.awt.*;

public class calculatorApp {
    @FXML
    private AnchorPane pane;

    // Number buttons
    @FXML
    private Button one, two, three, four, five, six, seven, eight, nine, zero;

    // Operator buttons
    @FXML
    private Button plus, minus, multiplication, division ;

    // Display field for showing the input and result
    @FXML
    private TextField fieldDisplay;

    // Action buttons
    @FXML
    private Button clear;

    // Action buttons
    @FXML
    private Button equals;

    // Action buttons
    @FXML
    private Button point;

    // StringBuilder to track current input
    StringBuilder currentText = new StringBuilder();

    // Calculator model for performing operations
    Calculator calculator = new Calculator();

    // Tracks if we should start a new number after an operation
    private boolean startNewNumber = true;  // Tracks if we should start a new number

    // Stores the last operator pressed
    private String operatorPressed = "";  // Stores the last operator

    /**
     * Clears the calculator's current state and resets the display.
     * Triggered by pressing the "AC" button.
     */
    @FXML
    void clear(ActionEvent event) {
        calculator.reset();  // Reset the calculator model
        fieldDisplay.clear();  // Clear the display
        currentText.setLength(0);  // Clear current input
        startNewNumber = true;  // Start new input
    }

    //Handles the "=" button press, which calculates and displays the result.
    @FXML
    void equal(ActionEvent event) {
        // Set the second operand when the equals button is pressed
        if (!startNewNumber) {
            calculator.setOperand2(Double.parseDouble(fieldDisplay.getText()));
        }

        // Perform the calculation
        calculator.calculate();

        // Display the result or handle errors
        if (calculator.isError()) {
            fieldDisplay.setText("Error");  // Display error if division by zero
        } else {
            fieldDisplay.setText(String.valueOf(calculator.getResult()));  // Show the result
        }

        startNewNumber = true;  // Start new number after calculation

    }

    // Adds a decimal point to the current number input.
    @FXML
    void floating(ActionEvent event) {
        if (!currentText.toString().contains(".")) {
            currentText.append(".");
            fieldDisplay.setText(currentText.toString());
        }
    }

    //Handles the number button press (0-9), updates the display.
    @FXML
    void number(ActionEvent event) {
        if (startNewNumber) {
            fieldDisplay.clear();  // Clear the display if a new number starts
            startNewNumber = false;
        }

        String value = ((Button)event.getSource()).getText();
        currentText.append(value);
        fieldDisplay.setText(currentText.toString());
    }

    //Handles the operator button press (+, -, ×, ÷), stores the operator.
    @FXML
    void operator(ActionEvent event) {
        // Get the operator clicked
        String operator = ((Button) event.getSource()).getText();

        // Convert the operator to char for the Calculator class
        char operatorChar = operator.charAt(0);

        // Set the first operand if this is the first operator pressed
        if (!startNewNumber) {
            calculator.setOperand1(Double.parseDouble(fieldDisplay.getText()));
            startNewNumber = true;  // Next input will be for the second operand
        }

        // Set the operator in the Calculator class
        calculator.setOperator(operatorChar);
        operatorPressed = operator;  // Store the last operator used
        currentText.setLength(0);  // Clear current input
    }

}
