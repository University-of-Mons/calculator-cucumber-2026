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
	@DisplayName("MyNumber with same value should be equal")
	void testEqualsSameValue() {
		// Two distinct MyNumber, constructed separately but containing the same value should be equal
		assertEquals(new MyNumber(value), number);
	}

	@Test
	@DisplayName("MyNumber with different value should not be equal")
	void testEqualsDifferentValue() {
		// Two MyNumbers containing a distinct value should not be equal
		assertNotEquals(new MyNumber(7), number);
	}

	@Test
	@DisplayName("MyNumber should be equal to itself")
	void testEqualsIdentity() {
		// Identity check (for coverage, as this should always be true)
		assertEquals(number, number);
	}

	@Test
	@DisplayName("MyNumber should not be equal to different type")
	void testEqualsDifferentType() {
		// number is of type MyNumber, while 8 is of type int, so not equal
		assertNotEquals(number, 8);
	}

	@Test
	@DisplayName("MyNumber should not be equal to different operation")
	void testEqualsDifferentOperation() {
		try {
			assertNotEquals(new Times(new ArrayList<>()), number);
		}
		catch (IllegalConstruction _) {
			fail();
		}
	}

	@Test
	@DisplayName("MyNumber toString should return numeric value")
	void testToString() {
		assertEquals(Integer.toString(value), number.toString());
	}

}
