package dev.jlucasbs.study.controllers;

import dev.jlucasbs.study.exception.UnsupportedMathOperationException;
import dev.jlucasbs.study.math.SimpleMath;
import dev.jlucasbs.study.request.converters.NumberConverter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
public class MathController {

    private final SimpleMath math = new SimpleMath();

    // http://localhost:8080/math/sum/3/5
    @RequestMapping("/sum/{numberOne}/{numberTwo}")
    public Double sum(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws UnsupportedMathOperationException {
        if (NumberConverter.isNumeric(numberOne) || NumberConverter.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please enter a numeric value");
        }

        return  math.sum(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
    }

    // http://localhost:8080/math/subtraction/3/5
    @RequestMapping("/subtraction/{numberOne}/{numberTwo}")
    public Double subtraction(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws UnsupportedMathOperationException {
        if (NumberConverter.isNumeric(numberOne) || NumberConverter.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please enter a numeric value");
        }

        return  math.subtraction(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
    }

    // http://localhost:8080/math/multiplication/3/5
    @RequestMapping("/multiplication/{numberOne}/{numberTwo}")
    public Double multiplication(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws UnsupportedMathOperationException {
        if (NumberConverter.isNumeric(numberOne) || NumberConverter.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please enter a numeric value");
        }

        return  math.multiplication(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
    }

    // http://localhost:8080/math/division/3/5
    @RequestMapping("/division/{numberOne}/{numberTwo}")
    public Double division(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws UnsupportedMathOperationException {
        if (NumberConverter.isNumeric(numberOne) || NumberConverter.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please enter a numeric value");
        }

        return  math.division(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
    }

    // http://localhost:8080/math/mean/3/5
    @RequestMapping("/mean/{numberOne}/{numberTwo}")
    public Double mean(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws UnsupportedMathOperationException {
        if (NumberConverter.isNumeric(numberOne) || NumberConverter.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please enter a numeric value");
        }

        return  math.mean(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
    }

    // http://localhost:8080/math/squareRoot/9
    @RequestMapping("/squareRoot/{number}")
    public Double squareRoot(@PathVariable("number") String number) throws UnsupportedMathOperationException {
        if (NumberConverter.isNumeric(number)) {
            throw new UnsupportedMathOperationException("Please enter a numeric value");
        }

        return  math.squareRoot(NumberConverter.convertToDouble(number));
    }
}
