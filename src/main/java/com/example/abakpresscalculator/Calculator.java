package com.example.abakpresscalculator;

import java.util.Stack;

public class Calculator {

    public static final String BAD_INPUT = "Некорректные входные данные";

    public String calculate(String string){
        try {
            Stack<Integer> operandStack = new Stack<Integer>();
            Stack<Character> operatorStack = new Stack<Character>();
            boolean unaryIsPossible = true;
            for (int i = 0; i < string.length(); i++)
                switch (string.charAt(i)){
                    case ' ':
                        break;
                    case '(':
                        operatorStack.push('(');
                        unaryIsPossible = true;
                        break;
                    case ')':
                        while (operatorStack.peek() != '(') {
                            calculateOperation(operandStack, operatorStack.pop());
                        }
                        operatorStack.pop();
                        unaryIsPossible = false;
                        break;
                    case '+':
                    case '-':
                    case '*':
                    case '/':
                        char currentOperator = string.charAt(i);
                        if (unaryIsPossible && currentOperator == '-') currentOperator = '#';
                        while (!operatorStack.empty() && getPriority(operatorStack.peek()) >= getPriority(currentOperator)){
                            calculateOperation(operandStack, operatorStack.pop());
                        }
                        operatorStack.push(currentOperator);
                        unaryIsPossible = true;
                        break;
                    default:
                        String operand = "";
                        while (i < string.length() && Character.isDigit(string.charAt(i)))
                            operand += string.charAt(i++);
                        --i;
                        operandStack.push(Integer.parseInt(operand));
                        unaryIsPossible = false;
                        break;
                }
            while (!operatorStack.empty()){
                calculateOperation(operandStack, operatorStack.pop());
            }
            return String.valueOf(operandStack.peek());
        } catch (Exception exception){
            return BAD_INPUT;
        }
    }

    void calculateOperation(Stack<Integer> operandStack, char op) {
        if (op == '#') {
            int a = operandStack.pop();
            operandStack.push(-a);
        }
        else {
            int b = operandStack.pop();
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
                    operandStack.push(a / b);
                    break;
            }
        }
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
