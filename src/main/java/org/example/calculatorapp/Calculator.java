package org.example.calculatorapp;

public class Calculator {
    double operand1;   // Stores the first operand
    double operand2;   // Stores the second operand
    char operator;     // Stores the arithmetic operator
    double result;     // Stores the result of the operation
    boolean isError;   // Tracks if an error occurred (e.g., division by zero)

    public void setOperand1(double operand){
        this.operand1 = operand;
    }    // Sets the first operand

    public void setOperand2(double operand){
        this.operand2 = operand;
    }    // Sets the second operand

    public void setOperator(char operator){
        this.operator = operator;
    }         // Sets the arithmetic operator

    public void calculate(){
        isError = false;
        switch(operator){
            case '+':
                result = operand1 + operand2;
                break;
            case '-':
                result = operand1 - operand2;
                break;
            case '×':
                result = operand1 * operand2;
                break;
            case '÷':
                if(operand2 != 0){
                    result = operand1 / operand2;
                } else {
                    isError = true;
                }
                break;
            default:
                isError = true;
                break;
        }
    }                    // Performs the calculation based on the operator
    public double getResult(){
        return result;
    }                  // Returns the result of the operation
    public boolean isError(){
        return isError;
    }                   // Returns true if an error occurred (e.g., division by zero)
    public void reset(){
        operand1 = 0;
        operand2 = 0;
        operator = ' ';
        result = 0;
        isError = false;
    }                        // Resets all fields (for a new calculation)
}
