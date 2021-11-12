package com.revature.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/*
 * In this class, we are writing unit test for the AritheticService
 * 
 * In particular, we have 4 different unit tests to attest the functionality of our checkInputs(String, input1, String input 2) method
 * 
 * Unit test: a test that is testing a single unit (module)
 * 
 * unit: typically a single method
 * 
 * As a tester, you should never say something is "bug free".
 * as a rule of thumb it is impossible to say something does not have bugs or not
 * but it is the role of someone writing tests or performing tests to uncover bugs
 * testers = bug hunters
 * 
 * Test driven development (TDD): 
 * 	- Designing the actual application around the tests
 *  - Take requirements > write tests > write application to pass those tests
 *  - opposite of done in this project
 *  - Code was written first before testing
 *  
 *  Pros:
 *  - Always have tests for your application ( code coverage)
 *  	- What % of the code is actually associated with the tests
 *  - If done correctly, could result in less bugs
 *  - Encourages good design and architecture
 *  
 *  Cons:
 *  - Hard to do if you are an inexperienced developer
 *  - Initially can slow down development
 * 
 */

/*
 * 
 * Positive vs negative testing
 * 
 * Test can be categorized as positive or negative
 * 
 * 	- Positive: a condition where the user is utilizing something correctly
 * 	- negative: a condition where the user is utilizing something INCORRECTLY
 */

/*
 * Ordering tests: by default, tests inside of a class will run in a random order. There is NO guaranteed order for the tests 
 * 
 * -But, if we want to specify the order, do
 *  1. put the @TestMethodOrder (OrderAnnotation.class) annotation on top of the test class
 *  2. On each test class, we can place the @order(...) annotation 
 */

/* JUnit 5 common assert methods (static methods belonging to the Assertions class){
* assertArrayEquals(int[] expected, int [] actual)
* assertEquals(int expected, int actual)
* assertTrue(boolean actual)
* assertFalse(boolean actual)
* fail(): automatically fails the test
*/

/*
 * Test case v. Test suite
 * 	- You will hear these two terms fairly often in the world of software testing
 * 	- It is always important to study and pick up and start using the technical terminology as thhis will both help you to bettter 
 * 		understand the topics as well as prepare you for client interviews and talking with co-workers
 *  
 *  Test case: a test method
 *  - a method annotated with a @Test
 *  
 *  Test suite: a collection of test classes
 *  - test classes: contain test methods
 *  - a test suite is a comprehensive suite of tests
 */

public class ArithmeticServiceTest {

	public ArthimaticService artithmeticService;

	@BeforeEach
	public void setUp() {
		this.artithmeticService = new ArthimaticService();
	}

	/*
	 * When ... then...
	 * 
	 * When input is 10.5 and 100.34, then our output should be 0
	 * 
	 * When input is "    " and 10.52 then our output should be 1
	 * 
	 * when input is 10.3 and "   "" then our outputs should be 2
	 * 
	 * when input is BOTH "  ", then our output should be 3
	 * 
	 */

	// When writing tests, we have the acronym AAA (Arrange, act, assert)
	@Test // Positive test
	public void testCheckInputsMethodWithNoBlankInputs() {
		// Arrange
		// our arrange happens to be in the beforeEach method, where we create the
		// ArithmeticService object
		// We are arranging the initial values that we need

		// Act
		// Here we are acting on what we want to test. We are invoking the checkInputs
		// method
		int result = this.artithmeticService.checkInputs("10.5", "100.34");

		// Assert
		// We want to assert that what we acted on gave us an appropriate output. The
		// output SHOULD be 0 if the logic is correct
		Assertions.assertEquals(0, result);

		// JUnit 5 common assert methods (static methods belonging to the Assertions
		// class){
		/*
		 * assertArrayEquals(int[] expected, int [] actual) assertEquals(int expected,
		 * int actual) assertTrue(boolean actual) assertFalse(boolean actual) fail():
		 * automatically fails the test
		 */
	}

	@Test // Negative test
	public void testCheckInputsMethodWithLeftBlankInputAndNonblankRightInput() {
		// Arrange

		// Act
		int result = this.artithmeticService.checkInputs("   ", "10.53");

		// Assert
		Assertions.assertEquals(1, result);
	}

	@Test // Negative test
	public void testCheckInputsMethodWithNonblankLeftInputAndBlankRightInput() {
		// Arrange

		// Act
		int result = this.artithmeticService.checkInputs("10.3", "  ");

		// Assert
		Assertions.assertEquals(2, result);
	}

	@Test // Negative test
	public void testCheckInputsMethodWithBothInputsBlank() {
		// Arrange

		// Act
		int result = this.artithmeticService.checkInputs("  ", "  ");

		// Assert
	}

}
