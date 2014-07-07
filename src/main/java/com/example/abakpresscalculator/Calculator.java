package com.example.abakpresscalculator;

import java.util.Stack;

public class Calculator {

    private Stack<Integer> operandStack = new Stack<Integer>();
    private Stack<Character> operatorStack = new Stack<Character>();

    public static final String BAD_INPUT = "Некорректные входные данные";

    public String calculate(String string){
        try {
            boolean unaryIsPossible = true;
            for (int i = 0; i < string.length(); i++)
                if (!Character.isWhitespace(string.charAt(i)))
                    if (string.charAt(i) == '(') {
                        operatorStack.push('(');
                        unaryIsPossible = true;
                    } else if (string.charAt(i) == ')') {
                        while (operatorStack.peek() != '(')
                            calculateOperation(operatorStack.pop());
                        operatorStack.pop();
                        unaryIsPossible = false;
                    } else if (isOperator(string.charAt(i))) {
                        char currentOperator = string.charAt(i);
                        if (unaryIsPossible && currentOperator == '-') currentOperator = '#';
                        while (!operatorStack.empty() && getPriority(operatorStack.peek()) >= getPriority(currentOperator))
                            calculateOperation(operatorStack.pop());
                        operatorStack.push(currentOperator);
                        unaryIsPossible = true;
                    } else {
                        String operand = "";
                        while (i < string.length() && Character.isDigit(string.charAt(i)))
                            operand += string.charAt(i++);
                        --i;
                        operandStack.push(Integer.parseInt(operand));
                        unaryIsPossible = false;
                    }
            while (!operatorStack.empty())
                calculateOperation(operatorStack.pop());
            return String.valueOf(operandStack.peek());
        } catch (Exception exception){
            return BAD_INPUT;
        }
    }

    void calculateOperation(char op) {
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

    private boolean isOperator(char c){
        return c=='+' || c=='-' || c=='*' || c=='/';
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
