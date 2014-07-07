package com.example.abakpresscalculator;

import android.test.AndroidTestCase;

import junit.framework.Assert;

public class CalculatorTest extends AndroidTestCase{

    public void testSimpleString1(){
        Calculator calculator = new Calculator();
        Assert.assertEquals(calculator.calculate("2*2"), "4");
    }

    public void testSimpleString2(){
        Calculator calculator = new Calculator();
        Assert.assertEquals(calculator.calculate("2*2+2"), "6");
    }

    public void testSimpleString3(){
        Calculator calculator = new Calculator();
        Assert.assertEquals(calculator.calculate("2+2*2"), "6");
    }

    public void testSimpleString4(){
        Calculator calculator = new Calculator();
        Assert.assertEquals(calculator.calculate("(2+2)*2"), "8");
    }

    public void testSimpleUnaryString1(){
        Calculator calculator = new Calculator();
        Assert.assertEquals(calculator.calculate("-2"), "-2");
    }

    public void testSimpleUnaryString2(){
        Calculator calculator = new Calculator();
        Assert.assertEquals(calculator.calculate("-2-2"), "-4");
    }

    public void testSimpleUnaryString3(){
        Calculator calculator = new Calculator();
        Assert.assertEquals(calculator.calculate("2--2"), "4");
    }

    public void testSimpleUnaryString4(){
        Calculator calculator = new Calculator();
        Assert.assertEquals(calculator.calculate("-(2-2)"), "0");
    }

    public void testSimpleWhiteSpaceString(){
        Calculator calculator = new Calculator();
        Assert.assertEquals(calculator.calculate("2 / 2 + 2"), "3");
    }

    public void testNotSoSimpleString1(){
        Calculator calculator = new Calculator();
        Assert.assertEquals(calculator.calculate("52+(1+2)*4-3"), "61");
    }

    public void testNotSoSimpleString2(){
        Calculator calculator = new Calculator();
        Assert.assertEquals(calculator.calculate("52+((1+2)*4)-3"), "61");
    }

    public void testNotSoSimpleString3(){
        Calculator calculator = new Calculator();
        Assert.assertEquals(calculator.calculate("(52+1+2)*4-3"), "217");
    }

    public void testNotSoSimpleString4(){
        Calculator calculator = new Calculator();
        Assert.assertEquals(calculator.calculate("1+2*5-5/22+(45+34)*-3"), "-226");
    }

    public void testBadString1(){
        Calculator calculator = new Calculator();
        Assert.assertEquals(calculator.calculate("5+"), Calculator.BAD_INPUT);
    }

    public void testBadString2(){
        Calculator calculator = new Calculator();
        Assert.assertEquals(calculator.calculate("6/0"), Calculator.BAD_INPUT);
    }

    public void testBadString3(){
        Calculator calculator = new Calculator();
        Assert.assertEquals(calculator.calculate("a--"), Calculator.BAD_INPUT);
    }

    public void testBadString4(){
        Calculator calculator = new Calculator();
        Assert.assertEquals(calculator.calculate("2+0.2"), Calculator.BAD_INPUT);
    }
}
