package com.example.abakpresscalculator;

import java.util.Stack;

public class Calculator {

    public static final String BAD_INPUT = "Некорректные входные данные";

    public String calculate(String string){
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
                        if(operatorStack.empty())
                            return BAD_INPUT;
                        if(calculateOperation(operandStack, operatorStack.pop())!=0)
                            return BAD_INPUT;
                    }
                    if(operatorStack.empty())
                        return BAD_INPUT;
                    operatorStack.pop();
                    unaryIsPossible = false;
                    break;
                case '+':
                case '-':
                case '*':
                case '/':
                    char currentOperator = string.charAt(i);
                    if (unaryIsPossible && currentOperator == '-') currentOperator = '#';
                    while (!operatorStack.empty() && getPriority(operatorStack.peek()) >= getPriority(currentOperator))
                        if(calculateOperation(operandStack, operatorStack.pop())!=0)
                            return BAD_INPUT;
                    operatorStack.push(currentOperator);
                    unaryIsPossible = true;
                    break;
                default:
                    if(!Character.isDigit(string.charAt(i)))
                        return BAD_INPUT;
                    String operand = "";
                    while (i < string.length() && Character.isDigit(string.charAt(i)))
                        operand += string.charAt(i++);
                    --i;
                    operandStack.push(Integer.parseInt(operand));
                    unaryIsPossible = false;
                    break;
            }
        while (!operatorStack.empty())
            if(calculateOperation(operandStack, operatorStack.pop())!=0)
                return BAD_INPUT;
        return String.valueOf(operandStack.peek());
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
