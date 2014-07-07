package com.example.abakpresscalculator;

import java.util.Stack;

public class Calculator implements ICalculator{

    public static final String BAD_INPUT = "Некорректные входные данные";

    public Calculator(){
    }

    public Calculator(String input){
        this.input = input;
    }

    private String input = "";
    private int result = -1;
    private boolean inputCorrect = false;
    private boolean calculated = false;

    public void setInput(String input) {
        this.input = input;
    }

    public boolean isInputCorrect() {
        if(!calculated) {
            calculate();
            calculated = true;
        }
        return inputCorrect;
    }

    public int getResult() {
        if(!calculated) {
            calculate();
            calculated = true;
        }
        return result;
    }

    private void calculate(){
        Stack<Integer> operandStack = new Stack<Integer>();
        Stack<Character> operatorStack = new Stack<Character>();
        boolean unaryIsPossible = true;
        for (int i = 0; i < input.length(); i++)
            switch (input.charAt(i)){
                case ' ':
                    break;
                case '(':
                    operatorStack.push('(');
                    unaryIsPossible = true;
                    break;
                case ')':
                    while (operatorStack.peek() != '(') {
                        if(operatorStack.empty())
                            return;
                        if(calculateOperation(operandStack, operatorStack.pop())!=0)
                            return;
                    }
                    if(operatorStack.empty())
                        return;
                    operatorStack.pop();
                    unaryIsPossible = false;
                    break;
                case '+':
                case '-':
                case '*':
                case '/':
                    char currentOperator = input.charAt(i);
                    if (unaryIsPossible && currentOperator == '-') currentOperator = '#';
                    while (!operatorStack.empty() && getPriority(operatorStack.peek()) >= getPriority(currentOperator))
                        if(calculateOperation(operandStack, operatorStack.pop())!=0)
                            return;
                    operatorStack.push(currentOperator);
                    unaryIsPossible = true;
                    break;
                default:
                    if(!Character.isDigit(input.charAt(i)))
                        return;
                    String operand = "";
                    while (i < input.length() && Character.isDigit(input.charAt(i)))
                        operand += input.charAt(i++);
                    --i;
                    operandStack.push(Integer.parseInt(operand));
                    unaryIsPossible = false;
                    break;
            }
        while (!operatorStack.empty())
            if(calculateOperation(operandStack, operatorStack.pop())!=0)
                return;
        inputCorrect = true;
        result = operandStack.peek();
    }

    int calculateOperation(Stack<Integer> operandStack, char op) {
        if (op == '#') {
            if(operandStack.empty())
                return -1;
            int a = operandStack.pop();
            operandStack.push(-a);
        }
        else {
            if(operandStack.empty())
                return -1;
            int b = operandStack.pop();
            if(operandStack.empty())
                return -1;
            int a = operandStack.pop();
            switch (op) {
                case '+':
                    operandStack.push(a + b);
                    break;
                case '-':
                    operandStack.push(a - b);
                    break;
                case '*':
                    operandStack.push(a * b);
                    break;
                case '/':
                    if(b==0)
                        return -1;
                    operandStack.push(a / b);
                    break;
            }
        }
        return 0;
    }

    private int getPriority(char op){
        switch (op){
            case '#':
                return 3;
            case '*':
            case '/':
                return 2;
            case '+':
            case '-':
                return 1;
            default:
                return 0;
        }
    }
}
