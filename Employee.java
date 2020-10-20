
public class Employee implements Comparable<Employee> {

	public String name;
	public int time;
	private static String[] names = new String[10];
	private static int employees;

	/*
	 * creates a new employee class with name equal to name and time equal to time
	 * stores new employees in an array of string called names and increments the
	 * static int employees by 1 if a new employee has a name that is already in the
	 * array it throws a new IllegalArgument exception
	 */
	public Employee(String name, int time) {
		int i;
		/*
		 * increments through the perfect size names array to find if the new name is
		 * already in there
		 */
		for (i = 0; i < employees; i++) {
			System.out.println("here");
			if (names[i].contentEquals(name)) {
				throw new IllegalArgumentException("an Employee with the same name is already logged in");
			}
		}
		// sets the name and time variables
		this.name = name;
		this.time = time;

		/*
		 * if the number of employees is less than the total length then the name can be
		 * inserted
		 */
		if (employees < names.length) {
			names[employees] = name;
			employees += 1;

			/*
			 * if not then the array size is doubled and old elements are added to new array
			 */
		} else {

			String[] old = names;
			names = new String[old.length * 2];
			for (i = 0; i < old.length; i++) {
				names[i] = old[i];
			}
			// adds the name to the table and increments employees
			names[employees] = name;
			employees += 1;
		}
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
	 * 8. If the minutes are over 59 or the hours are over 24 it will give an error since this is not
	 * a valid time in either case. *note* the time cannot be negative since hyphens are removed
	 */
	public static int inputToInt(String input) {
		String check = "0123456789";
		String removedChars = " -.";
		int i;
		for (i = 0; i < removedChars.length(); i++) {
			input = removeChar(input, removedChars.charAt(i));
		}
		input = removeChar(input, ' ');

		input.replaceAll("-", "");
		input = input.toLowerCase();
		String first = null;
		String second = null;
		int firstInt;
		int secondInt;
		for (i = 0; i < input.length(); i++) {
			if (input.charAt(i) == ':') {
				first = input.substring(0, i);
				second = input.substring(i + 1, i + 3);
			}
		}
		input = input + "   ";
		if (first == null) {
			if (check.contains(input.substring(1, 2))) {
				if (check.contains(input.substring(2, 3))) {
					if (check.contains(input.substring(3, 4))) {
						first = input.substring(0, 2);
						second = input.substring(2, 4);
					} else {
						first = input.substring(0, 1);
						second = input.substring(1, 3);
					}
				} else {
					first = input.substring(0, 2);
					second = "00";
				}

			} else {
				first = input.substring(0, 1);
				second = "00";
			}

		}
		firstInt = Integer.parseInt(first);
		secondInt = Integer.parseInt(second);

		if (firstInt < 13 && input.contains("am") || input.contains("pm")) {
			if (firstInt > 12) {
				throw new IllegalArgumentException("Do not say am or pm with military time.");
			}
			if (input.contains("am")) {
				if (firstInt == 12) {
					firstInt -= 12;
				}
			} else {
				if (firstInt != 12) {
					firstInt += 12;
				}
			}
		}
		int toReturn = firstInt * 100 + secondInt;
		if (toReturn > 2399 || toReturn < 0 || (toReturn % 100) > 59) {
			throw new IllegalArgumentException("Illegal Time");
		}
		return firstInt * 100 + secondInt;
	}

}
