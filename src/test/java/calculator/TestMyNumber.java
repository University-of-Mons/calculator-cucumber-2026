package calculator;

//Import Junit5 libraries for unit testing:
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

@DisplayName("MyNumber tests")
class TestMyNumber {

	private final int value =8;
	private MyNumber number;
	
	@BeforeEach
	void setUp() {
		number = new MyNumber(value);
	}

	@Test
	@DisplayName("MyNumber equality should work correctly")
	void testEquals() {
		// Two distinct MyNumber, constructed separately (using a different constructor) but containing the same value should be equal
		assertEquals(new MyNumber(value), number);
		// Two MyNumbers containing a distinct value should not be equal:
		int otherValue = 7;
		assertNotEquals(new MyNumber(otherValue),number);
		assertEquals(number, number); // Identity check (for coverage, as this should always be true)
		assertNotEquals(number, value); // number is of type MyNumber, while value is of type int, so not equal
		try {
			assertNotEquals(new Times(new ArrayList<>()), number);
		}
		catch (IllegalConstruction _) {fail();}
	}

	@Test
	@DisplayName("MyNumber toString should return numeric value")
	void testToString() {
		assertEquals(Integer.toString(value), number.toString());
	}

}
