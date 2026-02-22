package calculator;

//Import Junit5 libraries for unit testing:
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@DisplayName("Subtraction operation tests")
class TestMinus {

	private final int value1 = 8;
	private final int value2 = 6;
	private Minus op;
	private List<Expression> params;

	@BeforeEach
	void setUp() {
		  params = Arrays.asList(new MyNumber(value1),new MyNumber(value2));
		  try { op = new Minus(params); }
		  catch(IllegalConstruction _) { fail(); }
	}

	@Test
	@DisplayName("Minus constructor should reject null parameter list")
	void testConstructor1() {
		// It should not be possible to create an expression without null parameter list
		assertThrows(IllegalConstruction.class, () -> op = new Minus(null));
	}

	@SuppressWarnings("AssertBetweenInconvertibleTypes")
	@Test
	@DisplayName("Minus expression should not be same as Times expression")
	void testConstructor2() {
		// A Times expression should not be the same as a Minus expression
		try {
			assertNotSame(op, new Times(new ArrayList<>()));
		} catch (IllegalConstruction _) {
			fail();
		}
	}

	@Test
	@DisplayName("Two similar Minus expressions should be equal")
	void testEquals() {
		// Two similar expressions, constructed separately (and using different constructors) should not be equal
		List<Expression> p = Arrays.asList(new MyNumber(value1), new MyNumber(value2));
		try {
			Minus e = new Minus(p, Notation.INFIX);
			assertEquals(op, e);
		}
		catch(IllegalConstruction _) { fail(); }
	}

	@SuppressWarnings("ConstantConditions")
	@Test
	@DisplayName("Null comparison should not throw exception")
	void testNull() {
		assertDoesNotThrow(() -> op==null); // Direct way to to test if the null case is handled.
	}

	@Test
	@DisplayName("Two similar Minus expressions should have same hashcode")
	void testHashCode() {
		// Two similar expressions, constructed separately (and using different constructors) should have the same hashcode
		List<Expression> p = Arrays.asList(new MyNumber(value1), new MyNumber(value2));
		try {
			Minus e = new Minus(p, Notation.INFIX);
			assertEquals(e.hashCode(), op.hashCode());
		}
		catch(IllegalConstruction _) { fail(); }
	}



}
