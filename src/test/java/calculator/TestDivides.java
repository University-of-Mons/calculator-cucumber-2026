package calculator;

//Import Junit5 libraries for unit testing:
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@DisplayName("Division operation tests")
class TestDivides {

	private final int value1 = 8;
	private final int value2 = 6;
	private Divides op;
	private List<Expression> params;

	@BeforeEach
	void setUp() {
		  params = Arrays.asList(new MyNumber(value1), new MyNumber(value2));
		  try {
		  	op = new Divides(params);
			op.notation = Notation.INFIX; // reset the notation to infix (which is the default) before each test
		  }
		  catch(IllegalConstruction _) { fail(); }
	}

	@Test
	@DisplayName("Divides constructor should reject null parameter list")
	void testConstructor1() {
		// It should not be possible to create an expression without null parameter list
		assertThrows(IllegalConstruction.class, () -> op = new Divides(null));
	}

	@SuppressWarnings("AssertBetweenInconvertibleTypes")
	@Test
	@DisplayName("Divides expression should not be same as Times expression")
	void testConstructor2() {
		// A Times expression should not be the same as a Divides expression
		try {
			assertNotSame(op, new Times(new ArrayList<>()));
		} catch (IllegalConstruction _) {
			fail();
		}
	}

	@Test
	@DisplayName("Two similar Divides expressions should be equal")
	void testEquals() {
		// Two similar expressions, constructed separately (and using different constructors) should be equal
		List<Expression> p = Arrays.asList(new MyNumber(value1), new MyNumber(value2));
		try {
			Divides d = new Divides(p, Notation.INFIX);
			assertEquals(op, d);
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
	@DisplayName("Two similar Divides expressions should have same hashcode")
	void testHashCode() {
		// Two similar expressions, constructed separately (and using different constructors) should have the same hashcode
		List<Expression> p = Arrays.asList(new MyNumber(value1), new MyNumber(value2));
		try {
			Divides e = new Divides(p, Notation.INFIX);
			assertEquals(e.hashCode(), op.hashCode());
		}
		catch(IllegalConstruction _) { fail(); }
	}

}
