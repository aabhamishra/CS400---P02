////////////////////////////////////// CS400 File Header Information//////////////////////////////////////////
//Project Name: P02	- Employee Log Database																					//
//File Name: testProject.java																				//
//Name: Aabha Mishra, Ishan Chakravarty																					//
// Email: amishr36@wisc.edu																					//
// TA: Na Li																								//
// Lecturer: Prof. Florian Heimerl																			//																//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.api.Test;


	public class testEmployeeLog {
		
		String expectedOutput;
		String actualOutput;

		BackEndDevelop testLog = new BackEndDevelop();

		/**
		 * Tests getMin() when no entries are present
		 * Passes if the correct error message is present
		 * Fails otherwise
		 */
		public void testIsLongestNull() {

			actualOutput = testLog.getMin();
			expectedOutput = "There is no one clocked in yet.";

			assertEquals(expectedOutput, actualOutput);
		}
		/**
		 * Tests getMax() when no entries are present
		 * Passes if the correct error message is present
		 * Fails otherwise
		 */
		public void testIsShortestNull() {

			actualOutput = testLog.getMax();
			expectedOutput = "There is no one clocked in yet.";

			assertEquals(expectedOutput, actualOutput);
		}

		@BeforeEach
		public void InstanciateLog() {

			testLog.arrive("Emp1", 1000);
			testLog.arrive("Emp2", 0700);
			testLog.arrive("Emp3", 1300);
			testLog.arrive("Emp4", 1400);
			testLog.arrive("Emp5", 0500);
		}

		/**
		 * Tests to check if Illegal Argument exception is thrown when the same employee
		 * is added twice. Passes if exception is duly thrown Fails if no exception is
		 * thrown
		 */
		
		@Test
		  /**
		   * Test method to check which employee has checked into their shift closest to the current time
		   * Returns true if employee details match the employee who has been logged in for the shortest amount of time.

		   */
		  public void testisShortest() {
		 
			actualOutput = testLog.getMax();
			expectedOutput = "Emp4";

			assertEquals(expectedOutput, actualOutput);
		  }

		/**
		 * Tests method getMin() of BackEndDevelop Passes if method performs as expected
		 * Fails if errors are present
		 */
		@Test
		public void testIsLongest() {

			actualOutput = testLog.getMin();
			expectedOutput = "Emp5";

			assertEquals(expectedOutput, actualOutput);
		}

		/*
		 * Tests method searchTime() of BackEndDevelop Passes if method performs as
		 * expected Fails if errors are present
		 */
		@Test
		public void testSearchTime() {

			actualOutput = testLog.searchTime(1300);
			expectedOutput = "Emp3";

			assertEquals(expectedOutput, actualOutput);

			actualOutput = testLog.searchTime(0700);
			expectedOutput = "Emp2";

			assertEquals(expectedOutput, actualOutput);

			actualOutput = testLog.searchTime(1400);
			expectedOutput = "Emp4";

			assertEquals(expectedOutput, actualOutput);

		}

		/**
		 * Tests to check if Illegal Argument exception is thrown when time entered does
		 * not compare to any employee Passes if exception is duly thrown Fails if no
		 * exception is thrown
		 */
		@Test
		public void testSearchTimeException() {

			assertThrows(IllegalArgumentException.class, new Executable() {

				@Override
				public void execute() throws Throwable {
					testLog.searchTime(1600);
				}
			});
		}

	

}