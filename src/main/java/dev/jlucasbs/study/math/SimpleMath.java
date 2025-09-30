package dev.jlucasbs.study.math;

import dev.jlucasbs.study.exception.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.PathVariable;

import static dev.jlucasbs.study.request.converters.NumberConverter.convertToDouble;
import static dev.jlucasbs.study.request.converters.NumberConverter.isNumeric;

public class SimpleMath {
    public Double sum(Double numberOne, Double numberTwo) {
        return  numberOne + numberTwo;
    }

    public Double subtraction(Double numberOne, Double numberTwo) {
        return  numberOne - numberTwo;
    }

    public Double multiplication(Double numberOne, Double numberTwo) {
        return  numberOne * numberTwo;
    }

    public Double division(Double numberOne, Double numberTwo) {
        return  numberOne / numberTwo;
    }

    public Double mean(Double numberOne, Double numberTwo) {
        return  (numberOne + numberTwo) / 2;
    }

    public Double squareRoot(Double number) {
        return  Math.sqrt(number);
    }
}
