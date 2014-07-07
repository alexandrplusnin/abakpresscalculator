package com.example.abakpresscalculator;

import android.test.AndroidTestCase;

import junit.framework.Assert;

public class CalculatorTest extends AndroidTestCase{

    public void testSimpleString1(){
        ICalculator calculator = new Calculator();
        calculator.setInput("2*2");
        Assert.assertTrue(calculator.isInputCorrect());
        Assert.assertEquals(calculator.getResult(), 4);
    }

    public void testSimpleString2(){
        ICalculator calculator = new Calculator();
        calculator.setInput("2*2+2");
        Assert.assertTrue(calculator.isInputCorrect());
        Assert.assertEquals(calculator.getResult(), 6);
    }

    public void testSimpleString3(){
        ICalculator calculator = new Calculator();
        calculator.setInput("2+2*2");
        Assert.assertTrue(calculator.isInputCorrect());
        Assert.assertEquals(calculator.getResult(), 6);
    }

    public void testSimpleString4(){
        ICalculator calculator = new Calculator();
        calculator.setInput("(2+2)*2");
        Assert.assertTrue(calculator.isInputCorrect());
        Assert.assertEquals(calculator.getResult(), 8);
    }

    public void testSimpleUnaryString1(){
        ICalculator calculator = new Calculator();
        calculator.setInput("-2");
        Assert.assertTrue(calculator.isInputCorrect());
        Assert.assertEquals(calculator.getResult(), -2);
    }

    public void testSimpleUnaryString2(){
        ICalculator calculator = new Calculator();
        calculator.setInput("-2-2");
        Assert.assertTrue(calculator.isInputCorrect());
        Assert.assertEquals(calculator.getResult(), -4);
    }

    public void testSimpleUnaryString3(){
        ICalculator calculator = new Calculator();
        calculator.setInput("2--2");
        Assert.assertTrue(calculator.isInputCorrect());
        Assert.assertEquals(calculator.getResult(), 4);
    }

    public void testSimpleUnaryString4(){
        ICalculator calculator = new Calculator();
        calculator.setInput("-(2-2)");
        Assert.assertTrue(calculator.isInputCorrect());
        Assert.assertEquals(calculator.getResult(), 0);
    }

    public void testSimpleWhiteSpaceString(){
        ICalculator calculator = new Calculator();
        calculator.setInput("2 / 2 + 2");
        Assert.assertTrue(calculator.isInputCorrect());
        Assert.assertEquals(calculator.getResult(), 3);
    }

    public void testNotSoSimpleString1(){
        ICalculator calculator = new Calculator();
        calculator.setInput("52+(1+2)*4-3");
        Assert.assertTrue(calculator.isInputCorrect());
        Assert.assertEquals(calculator.getResult(), 61);
    }

    public void testNotSoSimpleString2(){
        ICalculator calculator = new Calculator();
        calculator.setInput("52+((1+2)*4)-3");
        Assert.assertTrue(calculator.isInputCorrect());
        Assert.assertEquals(calculator.getResult(), 61);
    }

    public void testNotSoSimpleString3(){
        ICalculator calculator = new Calculator();
        calculator.setInput("(52+1+2)*4-3");
        Assert.assertTrue(calculator.isInputCorrect());
        Assert.assertEquals(calculator.getResult(), 217);
    }

    public void testNotSoSimpleString4(){
        ICalculator calculator = new Calculator();
        calculator.setInput("1+2*5-5/22+(45+34)*-3");
        Assert.assertTrue(calculator.isInputCorrect());
        Assert.assertEquals(calculator.getResult(), -226);
    }

    public void testBadString1(){
        ICalculator calculator = new Calculator();
        calculator.setInput("-");
        Assert.assertFalse(calculator.isInputCorrect());
    }

    public void testBadString2(){
        ICalculator calculator = new Calculator();
        calculator.setInput("6/0");
        Assert.assertFalse(calculator.isInputCorrect());
    }

    public void testBadString3(){
        ICalculator calculator = new Calculator();
        calculator.setInput("a--");
        Assert.assertFalse(calculator.isInputCorrect());
    }

    public void testBadString4(){
        ICalculator calculator = new Calculator();
        calculator.setInput("2+0.2");
        Assert.assertFalse(calculator.isInputCorrect());
    }
}
