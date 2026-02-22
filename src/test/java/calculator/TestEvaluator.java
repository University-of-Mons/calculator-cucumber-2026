package calculator;

//Import Junit5 libraries for unit testing:
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

@DisplayName("Expression evaluator tests")
class TestEvaluator {

    private Calculator calc;
    private int value1, value2;

    @BeforeEach
    void setUp() {
        calc = new Calculator();
        value1 = 8;
        value2 = 6;
    }

    @Test
    @DisplayName("MyNumber evaluation should return its value")
    void testEvaluatorMyNumber() {
        assertEquals( value1, calc.eval(new MyNumber(value1)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"*", "+", "/", "-"})
    @DisplayName("Arithmetic operations should evaluate correctly")
    void testEvaluateOperations(String symbol) {
        List<Expression> params = Arrays.asList(new MyNumber(value1),new MyNumber(value2));
        try {
            //construct another type of operation depending on the input value
            //of the parameterised test
            switch (symbol) {
                case "+"	->	assertEquals( value1 + value2, calc.eval(new Plus(params)));
                case "-"	->	assertEquals( value1 - value2, calc.eval(new Minus(params)));
                case "*"	->	assertEquals( value1 * value2, calc.eval(new Times(params)));
                case "/"	->	assertEquals( value1 / value2, calc.eval(new Divides(params)));
                default		->	fail();
            }
        } catch (IllegalConstruction _) {
            fail();
        }
    }

}
