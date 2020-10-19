

public class Employee {

	private String name;
	private int time;

	public Employee(String name, int time) {
		this.name = name;
		this.time = time;
	}

	public int compareTo(Employee e2) {
		if (this.time > e2.time) {
			return 1;
		} else if (this.time < e2.time) {
			return -1;
		} else {
			throw new IllegalArgumentException("both employees have the same time");
		}

	}

	public int getTime() {
		return this.time;
	}

	public String getName() {
		return this.name;
	}

	public static boolean isValidInput(String input) {
		try {
			inputToTime(input);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static int inputToTime(String input) {
		String check = "0123456789";
		String removedChars = ". -";
		input.replaceAll(" ", "");
		int i;
		for (i = 0; i < removedChars.length(); i++) {
			input.replaceAll("" + removedChars.charAt(i), "");
		}
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
