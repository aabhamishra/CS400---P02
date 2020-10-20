
public class Employee implements Comparable<Employee> {

	public String name;
	public int time;

	/*
	 * creates a new employee class with name equal to name and time equal to time
	 */
	public Employee(String name, int time) {
		// sets the name and time variables
		this.name = name;
		this.time = time;
	}

	/*
	 * compares one employee to another based on their arrival times returns 1 if
	 * the first time is larger -1 if the second one is larger and throws an
	 * IllegalArgumentException otherwise.
	 */
	public int compareTo(Employee e2) {
		if (this.time > e2.time) {
			return 1;
		} else if (this.time < e2.time) {
			return -1;
		} else {
			return 0;
		}

	}

	/*
	 * returns the time field of the employee class
	 */
	public int getTime() {
		return this.time;
	}

	/*
	 * returns the name field
	 */
	public String getName() {
		return this.name;
	}

	/*
	 * returns true if the string input is valid and false otherwise
	 */
	public static boolean isValidInput(String input) {
		try {
			inputToInt(input);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/*
	 * a helper method that removes all chars from the String input that match the
	 * char called character
	 */
	public static String removeChar(String input, char character) {
		int i;
		for (i = 0; i < input.length(); i++) {
			if (input.charAt(i) == character) {
				if (i != input.length() - 1) {
					input = input.substring(0, i) + input.substring(i + 1, input.length());
					i -=1;
				} else {
					input = input.substring(0, i);
				}
			}
		}
		return input;
	}

		/*
	 * returns an int time representation of a given string throws an
	 * illegalArgumentException if the input is invalid
	 * 
	 * Things to know about this method -
	 * 
	 * 1. The first thing in the input string must be the time
	 * if there are letters or extraneous characters at the start
	 * of the input it will not work.
	 * 
	 * 2. The time can be entered with or without a : ie -
	 * 1:45 and 145 and 1242 and 125 are all valid inputs
	 * 
	 * 3. if there are 3 digits ie - 635 the assumed meaning of this is
	 * 6:35 ie it will always assume a missing 0 at the front for 3 digits
	 * 4 digits are always assumed to mean xx:xx and if there are more than 4 it will take
	 * the first 4
	 * 
	 * 4. if there are 2 or 1 digits it will assume those digits are the hour and that
	 * minutes are 00 ie 1 is 1:00 14 is 14:00 etc
	 * 
	 * 5. it will accept military time or pm/am but not both. if one is to use am/pm it has to
	 * be after the time since if the time is not first it will work, ie 12:00am will work
	 * 12am  154pm will all work
	 * 
	 * 6. if digits are interrupted by most characters that aren't digits then it will only
	 * count the first group of digits ie - 12AWEF24 would be read as 12 since A is not a valid
	 * character
	 * 
	 * 7. The method removes all spaces hyphens and periods, as well as lowercasing all elements
	 * before reading so 1.3.5-6 A -.-.- m would be read as 1356am
	 *
	 * 8. If the minutes are over 59 or the hours are over 24 it will give an error since this is not
	 * a valid time in either case. *note* the time cannot be negative since hyphens are removed
	 */
	public static int inputToInt(String input) {
		//this string is used to check if something is a digit via check.contains()
		String check = "0123456789";
		//this string is iterated through to remove all of these characters
		//from the input string via the removeCharhelper method
		String removedChars = " -.";
		int i;
		//iterates through removedChars
		for (i = 0; i < removedChars.length(); i++) {
			input = removeChar(input, removedChars.charAt(i));
		}
		//changes to lowerCase
		input = input.toLowerCase();
		
		//first will be the substring containing the hours, and second the minutes
		String first = null;
		String second = null;
		
		//firstInt holds the hours and secondInt the minutes
		int firstInt;
		int secondInt;
		//searches for a colon and if one is found separates into substrings accordingly
		for (i = 0; i < input.length(); i++) {
			if (input.charAt(i) == ':') {
				first = input.substring(0, i);
				second = input.substring(i + 1, i + 3);
			}
		}
		//input has whitespace added so the following operations work even if input would have been to small for them
		input = input + "   ";
		
		//if first is null there was no colon found so we assume the first space is a digit
		//and then check the next three to find out how many digits there are
		//if the first space is not a digit then parseint will throw an error and this is
		//an invalid input so it should
		if (first == null) {
			//checks second space for digit
			if (check.contains(input.substring(1, 2))) {
				//checks third space for digit
				if (check.contains(input.substring(2, 3))) {
					//checks fourth space for digit
					if (check.contains(input.substring(3, 4))) {
						//this is the case that the first4 characters are all digits
						//so we separate into two substrings of hours and minutes
						first = input.substring(0, 2);
						second = input.substring(2, 4);
					} else {
						//this is the case of 3 digits so we assume the first
						//is the hour and the second two are the minutes
						first = input.substring(0, 1);
						second = input.substring(1, 3);
					}
				} else {
					//this is the case of two digits so we assume they are both the hour
					//and minutes are 00
					first = input.substring(0, 2);
					second = "00";
				}

			} else {
				//this is the case of one digit so we assume it is the hour
				//and minutes are 00
				first = input.substring(0, 1);
				second = "00";
			}

		}
		//parses the two substrings into ints and assigns them to firstInt and secondInt
		firstInt = Integer.parseInt(first);
		secondInt = Integer.parseInt(second);
		
		// if the input contains am or pm changes the time
		if (input.contains("am") || input.contains("pm")) {
			//if the input has am/pm and a number greater than 12 then the input is invalid
			if (firstInt > 12) {
				throw new IllegalArgumentException("Do not use am or pm with military time.");
			}
			//if input is am it is only changed if firstInt (hours) is 12
			if (input.contains("am")) {
				if (firstInt == 12) {
					firstInt -= 12;
				}
			} else {
				//if input contains pm then it is changed unless firstInt (hours) is 12
				if (firstInt != 12) {
					firstInt += 12;
				}
			}
		}
		//creates an int called toReturn which holds the hours in the thousands and hundreds slot
		//and the minutes in the ones and tens places
		int toReturn = firstInt * 100 + secondInt;
		//checks that the time input is valid by making sure that hours are less than 24 and that 
		//minutes are not greater than 59
		if (toReturn > 2399 || toReturn < 0 || (toReturn % 100) > 59) {
			throw new IllegalArgumentException("Illegal Time");
		}
		//returns the int representation of the time
		return toReturn;
	}

}
