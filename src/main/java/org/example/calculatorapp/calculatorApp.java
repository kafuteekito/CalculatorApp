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

    @FXML
    private Button one, two, three, four, five, six, seven, eight, nine, zero;

    @FXML
    private Button plus, minus, multiplication, division ;

    @FXML
    private TextField fieldDisplay;

    @FXML
    private Button clear;

    @FXML
    private Button equals;

    @FXML
    private Button point;

    StringBuilder currentText = new StringBuilder();

    Calculator calculator = new Calculator();

    private boolean startNewNumber = true;  // Tracks if we should start a new number
    private String operatorPressed = "";  // Stores the last operator

    @FXML
    void clear(ActionEvent event) {
        calculator.reset();  // Reset the calculator model
        fieldDisplay.clear();  // Clear the display
        currentText.setLength(0);  // Clear current input
        startNewNumber = true;  // Start new input
    }

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

    @FXML
    void floating(ActionEvent event) {
        if (!currentText.toString().contains(".")) {
            currentText.append(".");
            fieldDisplay.setText(currentText.toString());
        }
    }

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
